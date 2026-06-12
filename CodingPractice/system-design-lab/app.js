const modal = document.querySelector("#questionModal");
const form = document.querySelector("#questionForm");
const toast = document.querySelector("#toast");
const questionGrid = document.querySelector("#questionGrid");
const questionCount = document.querySelector("#questionCount");
const promptNumber = document.querySelector("#promptNumber");
const promptCategory = document.querySelector("#promptCategory");
const promptTitle = document.querySelector("#promptTitle");
const promptText = document.querySelector("#promptText");
const promptFocusInput = document.querySelector("#promptFocusInput");
const promptAnswer = document.querySelector("#promptAnswer");
const promptFocus = document.querySelector("#promptFocus");
const editingStatus = document.querySelector("#editingStatus");
const editButton = document.querySelector("#editQuestion");
const saveChangesButton = document.querySelector("#saveQuestionChanges");
const resetButton = document.querySelector("#resetQuestion");
const importFile = document.querySelector("#importFile");

const builtInQuestions = {
  "banking-transactions": {
    number: "01",
    category: "System design · Banking infrastructure",
    title: "Design a banking transaction system",
    prompt: "Design a system that can handle banking transactions, including transfers and payments, at scale. Focus on fault tolerance and strong consistency. Explain how you would make the system highly available.",
    focus: ["Ledger integrity", "Idempotency", "Multi-region availability"],
    answer: `1. Clarify the scope
- Separate internal transfers from external ACH, wire, and card payments.
- Define scale, latency, availability, transaction limits, and settlement expectations.
- Establish invariants: no lost money, no duplicate debit, and every debit has an equal credit.

2. Core design
- Put stateless APIs behind an authenticated, rate-limited gateway.
- Use a transaction service for validation and idempotency.
- Keep an immutable double-entry ledger as the source of truth.
- Store a unique idempotency key with the transaction result.
- Commit ledger entries and a transactional outbox record atomically.

3. Consistency
- Use serializable database transactions or consensus-backed conditional writes.
- Serialize concurrent writes for the same account or account shard.
- Read balances from a strongly consistent source for spend decisions.
- Allow history, notifications, analytics, and search views to update eventually.

4. Availability and fault tolerance
- Run stateless services active-active across zones and regions.
- Give each account shard one write leader backed by quorum replication.
- Automatically elect a new leader when quorum remains available.
- During a network partition, reject or hold money movement in a region without quorum. Preserve correctness over write availability.
- Use bounded retries, circuit breakers, backpressure, reconciliation, tested backups, and regional failover drills.

5. Failure handling
- If the service crashes after commit, a retry returns the stored result by idempotency key.
- If the broker fails, the outbox publisher retries without blocking the ledger commit.
- For external payment rails, model pending, submitted, settled, failed, reversed, and reconciled states as a durable workflow.`
  },
  "credit-card-portal": {
    number: "02",
    category: "System design · Customer experience",
    title: "Design a credit card portal",
    prompt: "Design a secure, highly available credit card portal where customers can view balances and transactions, access statements, make payments, manage cards, configure alerts, and start disputes. Discuss authentication, API and data boundaries, payment consistency, caching, scale, security, and graceful degradation when downstream card systems are unavailable.",
    focus: ["Customer identity", "Card servicing", "Payments", "PCI security"],
    answer: `1. Requirements
- View current balance, available credit, pending and posted transactions, rewards, and statements.
- Make one-time or scheduled payments.
- Lock or replace a card, manage travel and alert settings, and open disputes.
- Support web and mobile clients with strong accessibility and auditability.

2. Architecture
- Place a web/mobile backend-for-frontend behind WAF, API gateway, authentication, and rate limiting.
- Split domain services into account summary, transaction history, statements, payments, card controls, notifications, and disputes.
- Use an aggregation service to compose the dashboard while keeping domain ownership separate.
- Integrate through adapters with systems of record instead of exposing legacy card platforms directly.

3. Data and consistency
- Cache read-heavy summaries and reference data with short TTLs, but clearly show the data timestamp.
- Treat payment submission and card-control changes as strongly consistent commands with idempotency keys.
- Store payment state as scheduled, submitted, processing, posted, failed, or canceled.
- Use events to refresh read models and notifications. Never use a stale cache to decide whether a payment or card-control command succeeded.

4. Security
- Use MFA and risk-based authentication, short-lived tokens, device/session controls, and step-up authentication for sensitive actions.
- Tokenize PAN data, minimize PCI scope, encrypt in transit and at rest, redact logs, and maintain immutable audit records.
- Apply least privilege, fraud controls, bot protection, and velocity limits.

5. Availability
- Run stateless services across multiple zones and regions.
- Degrade by capability: if statements are unavailable, balances and card lock may remain usable.
- Queue safe asynchronous work, but do not claim a payment succeeded until its durable command is accepted.
- Use circuit breakers around downstream systems and show honest pending or temporarily unavailable states.

6. Scale and operations
- Partition transaction history by account and time, use pagination, and generate statements asynchronously.
- Monitor login success, dashboard latency, payment completion, stale-data age, dependency health, fraud signals, and customer-visible error rates.`
  },
  "vcn-validation": {
    number: "03",
    category: "Case interview · VCN Handout 1",
    title: "Virtual Credit Card Number validation rules",
    prompt: "Similar to credit card numbers, the digits in virtual card numbers and associated transaction numbers may seem random, but they carry meaning. When a Capital One Virtual Credit Card is used, validate the data against the supplied business rules to arrive at a spend decision. Rules are applied to specific digits in the VCN and transaction ID. Each VCN transaction has a unique eight-digit transaction ID. Clarify the digit-level rules from the handout before implementing the validator.",
    focus: ["Rule interpretation", "Eight-digit transaction ID", "Spend decision", "Edge cases"],
    answer: `Important: Handout 1 describes the input shape but does not include the actual digit-position rules. Do not invent them. Ask for the remaining handout or a rule table before calculating a spend decision.

1. Clarify the contract
- Exact VCN length and whether leading zeros are valid.
- The meaning of each relevant digit or digit range.
- The eight transaction-ID digits used by each rule.
- Rule order, precedence, and whether all rules must pass.
- Allowed output decisions and rejection reason codes.
- Examples for approved, declined, and malformed inputs.

2. Validation pipeline
- Accept VCN and transaction ID as strings so leading zeros are preserved.
- Reject null, non-numeric, or incorrectly sized values before indexing digits.
- Parse named fields once, such as productCode or transactionType, rather than scattering numeric indexes through the code.
- Apply pure rule functions in the documented order.
- Return a structured result: decision, reason code, failed rule, and safe explanation.

3. Suggested model
validateFormat(vcn, transactionId)
parseFields(vcn, transactionId)
evaluateRules(fields, ruleSet)
return SpendDecision(APPROVE | DECLINE, reasonCode)

4. Engineering safeguards
- Represent rules as named, versioned configuration when business teams change them frequently.
- Keep an immutable audit record of rule-set version and outcome.
- Add table-driven tests for every boundary, leading zeros, conflicting rules, and all example cases.
- Never log the full VCN; mask or tokenize sensitive values.

5. Interview approach
- Restate each rule in plain English.
- Walk through one sample digit by digit.
- Separate input validation from business-rule evaluation.
- Explain time and space complexity, then discuss maintainability and test coverage.`
  }
};

let activeQuestionId = "banking-transactions";
const editorFields = [promptCategory, promptTitle, promptText, promptFocusInput, promptAnswer];

function getCustomQuestions() {
  return JSON.parse(localStorage.getItem("systemDesignQuestions") || "[]");
}

function getOverrides() {
  return JSON.parse(localStorage.getItem("systemDesignQuestionOverrides") || "{}");
}

function getQuestion(id) {
  const base = builtInQuestions[id] || getCustomQuestions().find((item) => item.id === id);
  if (!base) return null;
  return { ...base, ...(getOverrides()[id] || {}) };
}

function showToast(message) {
  toast.textContent = message;
  toast.classList.add("show");
  window.setTimeout(() => toast.classList.remove("show"), 2600);
}

function renderFocus(focus) {
  promptFocus.replaceChildren(...focus.map((item) => {
    const tag = document.createElement("span");
    tag.textContent = item;
    return tag;
  }));
}

function setEditMode(editing) {
  editorFields.forEach((field) => field.toggleAttribute("readonly", !editing));
  editButton.hidden = editing;
  saveChangesButton.hidden = !editing;
  editingStatus.textContent = editing ? "Editing · save changes when finished" : "Answer saved in this browser";
  if (editing) promptTitle.focus();
}

function selectQuestion(id, card) {
  const question = getQuestion(id);
  if (!question) return;
  activeQuestionId = id;
  document.querySelectorAll(".question-card").forEach((item) => item.classList.remove("active"));
  card.classList.add("active");
  promptNumber.textContent = question.number || "NEW";
  promptCategory.value = question.category || "";
  promptTitle.value = question.title || "";
  promptText.value = question.prompt || "";
  promptFocusInput.value = (question.focus || []).join(", ");
  promptAnswer.value = question.answer || "";
  renderFocus(question.focus || []);
  setEditMode(false);
}

function refreshCard(id, question) {
  const card = questionGrid.querySelector(`[data-question-id="${id}"]`);
  if (!card) return;
  card.querySelector(":scope > strong").textContent = question.title;
  card.querySelector(":scope > p").textContent = question.prompt;
}

document.querySelector("#openQuestionModal").addEventListener("click", () => modal.showModal());

document.querySelector("#saveQuestion").addEventListener("click", (event) => {
  event.preventDefault();
  if (!form.reportValidity()) return;
  const questions = getCustomQuestions();
  const question = {
    id: `custom-${Date.now()}`,
    title: document.querySelector("#newQuestionTitle").value.trim(),
    category: document.querySelector("#newQuestionCategory").value,
    prompt: document.querySelector("#newQuestionPrompt").value.trim(),
    answer: document.querySelector("#newQuestionAnswer").value.trim(),
    focus: ["Custom prompt", "Practice question"],
    createdAt: new Date().toISOString()
  };
  questions.push(question);
  localStorage.setItem("systemDesignQuestions", JSON.stringify(questions));
  renderCustomQuestion(question, questions.length + Object.keys(builtInQuestions).length);
  updateQuestionCount();
  form.reset();
  modal.close();
  showToast("Question added");
});

questionGrid.addEventListener("click", (event) => {
  const card = event.target.closest(".question-card");
  if (card) selectQuestion(card.dataset.questionId, card);
});

editButton.addEventListener("click", () => setEditMode(true));

saveChangesButton.addEventListener("click", () => {
  const question = {
    category: promptCategory.value.trim(),
    title: promptTitle.value.trim(),
    prompt: promptText.value.trim(),
    focus: promptFocusInput.value.split(",").map((item) => item.trim()).filter(Boolean),
    answer: promptAnswer.value.trim()
  };
  const overrides = getOverrides();
  overrides[activeQuestionId] = question;
  localStorage.setItem("systemDesignQuestionOverrides", JSON.stringify(overrides));
  renderFocus(question.focus);
  refreshCard(activeQuestionId, question);
  setEditMode(false);
  showToast("Question and answer saved locally");
});

resetButton.addEventListener("click", () => {
  const overrides = getOverrides();
  delete overrides[activeQuestionId];
  localStorage.setItem("systemDesignQuestionOverrides", JSON.stringify(overrides));
  const card = questionGrid.querySelector(`[data-question-id="${activeQuestionId}"]`);
  const base = builtInQuestions[activeQuestionId] || getCustomQuestions().find((item) => item.id === activeQuestionId);
  if (base && card) {
    refreshCard(activeQuestionId, base);
    selectQuestion(activeQuestionId, card);
    showToast("Restored original content");
  }
});

function renderCustomQuestion(question, displayNumber) {
  const card = document.createElement("button");
  card.className = "question-card";
  card.type = "button";
  card.dataset.questionId = question.id;
  card.innerHTML = `
    <span class="question-card-top"><b>${String(displayNumber).padStart(2, "0")}</b><i>Custom</i></span>
    <strong></strong><p></p>
    <span class="question-card-action">Review or edit <b>→</b></span>
  `;
  const display = getQuestion(question.id) || question;
  card.querySelector("strong").textContent = display.title;
  card.querySelector("p").textContent = display.prompt;
  questionGrid.append(card);
}

function updateQuestionCount() {
  const total = Object.keys(builtInQuestions).length + getCustomQuestions().length;
  questionCount.textContent = `${total} ${total === 1 ? "question" : "questions"}`;
}

document.querySelector("#exportQuestions").addEventListener("click", () => {
  const data = {
    exportedAt: new Date().toISOString(),
    customQuestions: getCustomQuestions(),
    overrides: getOverrides()
  };
  const blob = new Blob([JSON.stringify(data, null, 2)], { type: "application/json" });
  const link = document.createElement("a");
  link.href = URL.createObjectURL(blob);
  link.download = "system-design-lab-backup.json";
  link.click();
  URL.revokeObjectURL(link.href);
  showToast("Question bank exported");
});

document.querySelector("#importQuestions").addEventListener("click", () => importFile.click());
importFile.addEventListener("change", async () => {
  const file = importFile.files[0];
  if (!file) return;
  try {
    const data = JSON.parse(await file.text());
    if (!Array.isArray(data.customQuestions) || typeof data.overrides !== "object") throw new Error("Invalid format");
    localStorage.setItem("systemDesignQuestions", JSON.stringify(data.customQuestions));
    localStorage.setItem("systemDesignQuestionOverrides", JSON.stringify(data.overrides));
    window.location.reload();
  } catch {
    showToast("Could not import that JSON file");
  }
});

const savedQuestions = getCustomQuestions();
savedQuestions.forEach((question, index) => {
  if (!question.id) question.id = `custom-saved-${index}`;
  renderCustomQuestion(question, index + Object.keys(builtInQuestions).length + 1);
});
updateQuestionCount();
selectQuestion(activeQuestionId, questionGrid.querySelector(`[data-question-id="${activeQuestionId}"]`));

const notes = document.querySelector("#personalNotes");
const saveStatus = document.querySelector("#saveStatus");
notes.value = localStorage.getItem("bankingSystemDesignNotes") || "";
let saveTimer;
notes.addEventListener("input", () => {
  saveStatus.textContent = "Saving...";
  window.clearTimeout(saveTimer);
  saveTimer = window.setTimeout(() => {
    localStorage.setItem("bankingSystemDesignNotes", notes.value);
    saveStatus.textContent = "Saved locally";
  }, 450);
});
document.querySelector("#clearNotes").addEventListener("click", () => {
  notes.value = "";
  localStorage.removeItem("bankingSystemDesignNotes");
  saveStatus.textContent = "Notes cleared";
});

const markReviewedButton = document.querySelector("#markReviewed");
const readinessValue = document.querySelector("#readinessValue");
const progressBar = document.querySelector("#progressBar");
function setReviewedState() {
  readinessValue.textContent = "100%";
  progressBar.style.width = "100%";
  markReviewedButton.textContent = "Reviewed ✓";
}
if (localStorage.getItem("bankingSystemDesignReviewed") === "true") setReviewedState();
markReviewedButton.addEventListener("click", () => {
  localStorage.setItem("bankingSystemDesignReviewed", "true");
  setReviewedState();
  showToast("Question marked as reviewed");
});

const sections = [...document.querySelectorAll("article section[id]")];
const railLinks = [...document.querySelectorAll(".rail > a")];
const observer = new IntersectionObserver((entries) => {
  const visible = entries.find((entry) => entry.isIntersecting);
  if (!visible) return;
  railLinks.forEach((link) => link.classList.toggle("active", link.getAttribute("href") === `#${visible.target.id}`));
}, { rootMargin: "-20% 0px -65%", threshold: 0 });
sections.forEach((section) => observer.observe(section));
