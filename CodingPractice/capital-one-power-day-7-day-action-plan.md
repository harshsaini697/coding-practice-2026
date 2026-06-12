# Capital One Senior Lead Software Engineer

## Seven-Day Power Day Action Plan

Plan dates: June 9-15, 2026

Target: Senior Lead Software Engineer Power Day

Recommended daily commitment:

- Weekdays: 2.5-3 hours
- Weekend/full mock day: 4.5-5 hours
- Final day: 2 hours, then stop heavy preparation

## Expected Interview Shape

The recruiter's agenda is the source of truth, but recent public Senior Software Engineer and Technical Lead reports commonly describe four rounds:

1. Practical coding or object-oriented implementation
2. System design
3. Technical case study, debugging, or problem-solving
4. Behavioral and leadership

Capital One's current Senior Lead postings emphasize:

- Leading a portfolio of technology projects
- Distributed microservices and full-stack systems
- Cloud-based solutions and AWS
- Regulatory and customer requirements
- Mentoring and raising engineering standards
- Coding or evaluating code
- Collaboration with product managers

Recent candidate reports are anecdotal, but recurring themes include banking-oriented problems, incremental requirements, maintainable code, specific technology choices, AWS discussion, business reasoning, code debugging, and STAR behavioral answers.

## Senior Lead Answer Standard

Every round should demonstrate more than individual implementation ability.

Use this checklist:

- Clarify the objective before solving.
- Identify customer, business, security, regulatory, and operational constraints.
- State assumptions explicitly.
- Explain alternatives and trade-offs.
- Make a recommendation instead of listing options indefinitely.
- Discuss failure modes, observability, rollout, and recovery.
- Explain how you would align engineers, product, risk, and operations.
- Communicate at both executive and implementation depth.

## Day 1: Baseline And Story Inventory

Date: Tuesday, June 9

Time: 2.5-3 hours

### Objectives

- Confirm the interview format with the recruiter.
- Establish a baseline in all four rounds.
- Build the raw material for behavioral answers.

### Tasks

- [ ] Send the recruiter a concise request for the exact round names, durations, coding environment, and permitted language.
- [ ] Read the target job description and highlight five responsibilities.
- [ ] Record a 90-second "Tell me about yourself" response.
- [ ] Complete one 35-minute practical coding baseline.
- [ ] Outline one system design in 30 minutes.
- [ ] Answer three behavioral questions aloud.
- [ ] Review the existing virtual-card case notes for 30 minutes.

### Recruiter Questions

1. What are the exact Power Day rounds and durations?
2. Is the coding round algorithmic, object-oriented, or requirements-based?
3. Which coding languages and IDEs are supported?
4. Is the system-design round expected to include AWS service selection?
5. Does the case round include code review or live coding?
6. Are breaks scheduled between interviews?

### Coding Baseline

Implement an in-memory account service with:

- `createAccount(accountId)`
- `deposit(accountId, amount)`
- `transfer(fromId, toId, amount)`
- `getTopActivity(n)`

Evaluate yourself on:

- Requirements clarification
- Correctness and edge cases
- Data-model clarity
- Naming and maintainability
- Time and space complexity
- Tests performed aloud

### System-Design Baseline

Design a credit-card transaction authorization service.

Cover:

- Functional and non-functional requirements
- APIs and data model
- Idempotency
- Cumulative spending limits
- Fraud controls
- Availability and consistency
- Auditability
- AWS deployment

### Behavioral Baseline Questions

1. Tell me about a complex initiative you led across multiple teams.
2. Tell me about a time you disagreed with product or another senior engineer.
3. Tell me about a production failure for which you were accountable.

### Deliverables

- [ ] Interview-format notes
- [ ] One-page job-description alignment
- [ ] List of 10 possible behavioral stories
- [ ] Baseline scores from 1-5 for every round

## Day 2: Practical Coding And Code Quality

Date: Wednesday, June 10

Time: 3 hours

### Objectives

- Write correct, maintainable code under incremental requirements.
- Speak clearly while coding.
- Demonstrate Senior Lead code-review judgment.

### Study Block: 35 Minutes

Review:

- Hash maps, sets, queues, heaps, sorting, and comparators
- Object-oriented modeling
- Input validation
- Idempotency
- State transitions
- Unit-test design
- Complexity analysis

If using Java, review:

- `HashMap`, `HashSet`, `PriorityQueue`, and custom comparators
- Records or immutable data objects
- `equals` and `hashCode`
- Exceptions versus result types
- Thread-safety boundaries

### Timed Coding Drill 1: Banking Service

Time: 45 minutes

Build the account service from Day 1, then add requirements one at a time:

1. Reject invalid transfers.
2. Track total account activity.
3. Return the top `n` accounts by activity.
4. Break ties alphabetically.
5. Add scheduled transfers.
6. Make repeated requests idempotent by `transactionId`.

Questions to answer aloud:

- What assumptions am I making?
- Which data structure supports each operation?
- What happens when an account does not exist?
- How do I avoid partially applying a transfer?
- How would this design change with concurrent requests?
- What would I change for production persistence?

### Timed Coding Drill 2: Transaction Validator

Time: 45 minutes

Implement:

```text
validate(card, transaction) -> decision
```

Rules:

- Card must be active.
- Card must not be expired.
- Merchant must match the verified merchant restriction.
- Amount must be below the per-transaction limit.
- Cumulative spending must remain below the lifetime limit.
- A duplicate `transactionId` returns the original result.

Incremental changes:

1. Add single-use cards.
2. Add reusable cards.
3. Add incremental authorization.
4. Add safe decline-reason codes.

### Code-Review Drill

Time: 35 minutes

Review a small service and look for:

- Incorrect boundary conditions
- Mutable shared state
- Missing input validation
- Duplicate processing
- Partial updates
- Logging of sensitive data
- Unbounded retries
- Missing tests

Practice saying:

> I will first confirm the intended behavior, identify correctness and security defects, then separate required fixes from maintainability improvements.

### Coding-Round Question Bank

1. Design an in-memory banking system with deposits, transfers, and activity rankings.
2. Implement a card-authorization rules engine whose requirements change incrementally.
3. Build a transaction deduplication service using idempotency keys.
4. Implement a rolling transaction-velocity checker.
5. Build a rewards tracker that supports category multipliers and monthly caps.
6. Debug a function that calculates recent customer activity but mishandles time boundaries.
7. Review code that performs a balance read and write separately under concurrency.
8. Add tests to a payment function with incomplete failure handling.

### Exit Criteria

- [ ] Finish core requirements within 35 minutes.
- [ ] Explain complexity without prompting.
- [ ] Test happy path, invalid input, boundary, and duplicate request.
- [ ] Avoid silent assumptions.
- [ ] Keep code readable while requirements change.

## Day 3: Senior-Level System Design

Date: Thursday, June 11

Time: 3 hours

### Objectives

- Lead a 45-minute design discussion.
- Make concrete AWS and datastore choices.
- Show financial-system correctness and operational maturity.

### Design Framework

Use this order:

1. Clarify users and business objective.
2. Define functional requirements.
3. Define scale and service-level objectives.
4. Identify hard invariants.
5. Design APIs and data model.
6. Draw the high-level architecture.
7. Deep-dive into two critical components.
8. Address failure modes and security.
9. Cover observability and rollout.
10. Summarize the recommendation and trade-offs.

### Full Mock Design

Time: 50 minutes

Design a reusable virtual-card platform supporting:

- Card creation and revocation
- Merchant, amount, and expiration restrictions
- Single-use and reusable modes
- Payment authorization
- Cumulative limits
- Idempotency
- Audit events
- Multi-region availability

Required deep dives:

- Atomic balance enforcement
- Retry handling
- Regional failure
- Tokenization and PCI boundaries
- Reconciliation

### Second Design Outline

Time: 35 minutes

Design a credit-card application and account-management platform:

- Submit an application
- Track application status
- Create online access
- View cards and transactions
- Pay the balance through a third party
- Generate daily, weekly, and monthly reports

### AWS Review

Time: 35 minutes

Be able to explain when and why you would consider:

- API Gateway or an application load balancer
- ECS/EKS or Lambda
- RDS/Aurora versus DynamoDB
- SQS, SNS, EventBridge, or managed Kafka
- ElastiCache
- KMS and Secrets Manager
- CloudWatch metrics, logs, traces, and alarms
- Multi-AZ versus multi-region deployment

Do not list services without connecting them to requirements.

### System-Design Question Bank

1. Design a credit-card authorization system.
2. Design a virtual-card service with configurable restrictions.
3. Design a credit-card application and account-management portal.
4. Design a payment system integrating with an external payment processor.
5. Design a rewards platform with category multipliers and retroactive adjustments.
6. Design real-time transaction notifications.
7. Design daily, weekly, and monthly transaction analytics.
8. Design a fraud-rules platform that supports safe configuration changes.
9. Design an immutable financial ledger and reconciliation system.
10. Design a multi-region service that must not exceed a global spending limit.

### Likely Deep-Dive Questions

1. Which operations require strong consistency?
2. How do retries avoid duplicate charges?
3. What happens if the service crashes after committing but before responding?
4. How do you protect sensitive card data?
5. How do you evolve event and database schemas safely?
6. What happens during a regional network partition?
7. How do you reconcile with an external payment network?
8. What metrics would cause you to stop rollout?
9. How would you reduce `p99` latency without stale reads?
10. How would you explain the architecture to a product executive?

### Exit Criteria

- [ ] Clarify requirements before drawing.
- [ ] State at least three explicit invariants.
- [ ] Choose technologies and explain why.
- [ ] Discuss alternatives and reject them with reasons.
- [ ] Cover security, observability, rollout, and recovery.
- [ ] Finish with a concise recommendation.

## Day 4: Technical Case Study

Date: Friday, June 12

Time: 3 hours

### Objectives

- Structure ambiguous business and technical problems.
- Debug code while preserving the business objective.
- Make a clear recommendation backed by evidence.

Capital One's official case guidance emphasizes asking questions, thinking like a business owner, sharing reasoning, explaining complex ideas clearly, and recognizing that multiple answers may be valid.

### Case Framework

Use `OBJECTIVE`:

- **O**bjective: What customer or business outcome matters?
- **B**oundaries: What is in and out of scope?
- **J**udgment criteria: How will success be measured?
- **E**vidence: What facts, data, and assumptions are available?
- **C**hoices: What options and trade-offs exist?
- **T**echnical analysis: What design or code changes are needed?
- **I**mplementation: How should this roll out safely?
- **V**alidation: What metrics and tests prove it works?
- **E**xecutive recommendation: What should we do and why?

### Full Case Drill

Time: 60 minutes

Case:

Capital One wants to reduce fraud by offering reusable virtual card numbers with merchant, amount, and expiration restrictions.

Work through:

1. Clarifying questions
2. Fraud definition
3. Customer and business success metrics
4. Validation flow
5. Duplicate and concurrent requests
6. Database outage
7. Merchant-ID changes
8. Settlement adjustment
9. Rollout recommendation
10. Executive summary

Use the existing file:

`CodingPractice/capital-one-senior-lead-case-study-practice.md`

### Debugging Case Drill

Time: 45 minutes

Review code that:

- Reads a balance from a replica.
- Checks whether funds are available.
- Calls an external network.
- Updates the primary database afterward.
- Publishes an event directly.

Identify:

- Stale-read overspending
- Missing idempotency
- Crash inconsistency
- Dual-write event loss
- Sensitive logging
- Unbounded retries

Recommend:

- Atomic conditional writes
- Durable authorization states
- Stable transaction IDs
- Transactional outbox
- Safe logs
- Bounded retries and reconciliation

### Case-Round Question Bank

1. Should Capital One launch reusable virtual cards? Recommend controls and rollout.
2. Fraud declines increased by 40%. Is this an attack, configuration issue, or regression?
3. A merchant changed payment processors and subscriptions are being declined. What should change?
4. A rewards promotion increased engagement but also increased fraud losses. What would you recommend?
5. A database optimization reduced latency but caused cumulative-limit overages. How would you respond?
6. A code sample applies transaction-validation rules incorrectly. Identify and prioritize fixes.
7. Product wants to approve transactions when the payment-network result is unknown. How do you respond?
8. A new fraud rule reduces losses but increases false declines. How would you decide whether to keep it?
9. A payment processor is cheaper but has worse timeout behavior. How would you evaluate migration?
10. An executive wants a simpler architecture and faster launch. Explain the value of safety mechanisms.

### Case Opening

Practice this aloud:

> Before recommending a solution, I would like to clarify the customer problem, business objective, risk tolerance, expected scale, and success metrics. I will then compare the options, identify the main failure modes, and recommend a safe rollout.

### Case Closing

Practice this aloud:

> My recommendation is to proceed with a limited rollout using the safer design. It protects the core financial invariant, gives us measurable customer and fraud outcomes, and provides a clear rollback path. I would expand only when the guardrail metrics remain within the agreed thresholds.

### Exit Criteria

- [ ] Ask at least five useful clarifying questions.
- [ ] Separate facts from assumptions.
- [ ] Tie technical choices to business impact.
- [ ] Make one recommendation.
- [ ] Include rollout, metrics, and rollback.
- [ ] Explain the answer without unnecessary jargon.

## Day 5: Behavioral And Senior Lead Leadership

Date: Saturday, June 13

Time: 3.5-4 hours

### Objectives

- Prepare eight strong STAR stories.
- Demonstrate organization-level technical leadership.
- Connect engineering work to customers and business results.

### STAR-L Structure

- **S**ituation: Give only the context needed.
- **T**ask: State your ownership and stakes.
- **A**ction: Spend most of the answer here.
- **R**esult: Quantify technical and business impact.
- **L**earning: Explain what changed in your leadership afterward.

Target answer length: 2-3 minutes.

### Required Story Inventory

Prepare one story for each:

1. Led a complex project across multiple teams.
2. Influenced without direct authority.
3. Resolved a technical disagreement.
4. Managed a production incident.
5. Improved reliability, security, or compliance.
6. Mentored engineers and raised team capability.
7. Made a difficult trade-off under time pressure.
8. Failed, learned, and changed your approach.
9. Simplified an architecture or reduced operational cost.
10. Challenged a product request while preserving partnership.

For every story, write:

- Scope and stakeholders
- Your specific decision
- Alternatives considered
- Conflict or ambiguity
- Measurable result
- What you learned

### Behavioral Question Bank

1. Tell me about a portfolio of projects you led.
2. Tell me about a time you influenced a decision without authority.
3. Tell me about a conflict with a product manager.
4. Tell me about a disagreement with another senior technical leader.
5. Tell me about a system failure and your role in the response.
6. Tell me about a time you balanced delivery speed with risk.
7. Tell me about a decision involving security, regulation, or compliance.
8. Tell me about an engineer you mentored.
9. Tell me about a technical standard you introduced across teams.
10. Tell me about a project that did not meet its goals.
11. Tell me about a time priorities changed suddenly.
12. Tell me about a time you inherited a weak or unclear architecture.
13. How do you decide when to build versus buy?
14. How do you keep senior engineers aligned without becoming a bottleneck?
15. How do you communicate technical risk to executives?
16. Why Capital One?
17. Why are you leaving your current or last company?
18. Why Senior Lead rather than an individual team-level role?

### Senior Lead Follow-Ups

Expect:

- What did you personally do?
- Who disagreed and why?
- What alternatives did you reject?
- How did you measure success?
- What would you do differently?
- How did the change affect customers?
- How did you raise the capability of others?
- How did you ensure the solution lasted after you moved on?

### Prepared Motivation Answer

> I have grown significantly in my current role, and I am looking for an opportunity where I can operate at greater scale, lead complex engineering initiatives across teams, and have broader customer impact. Capital One is compelling because technology is central to the business, and the Senior Lead role combines hands-on architecture, engineering leadership, mentoring, and partnership with product. That combination aligns closely with how I want to contribute in the next stage of my career.

### Exit Criteria

- [ ] Eight stories have quantified outcomes.
- [ ] No story spends more than 30 seconds on background.
- [ ] Actions consistently use "I," not only "we."
- [ ] Every story demonstrates judgment or influence.
- [ ] Answers include learning without undermining the result.

## Day 6: Full Simulated Power Day

Date: Sunday, June 14

Time: 4.5-5 hours

### Rules

- Use the same language and environment planned for the interview.
- Keep the camera on if the real interview is virtual.
- Speak continuously through decisions.
- Take 10-minute breaks between rounds.
- Do not look up solutions during a round.
- Record audio or video if possible.

### Round 1: Coding

Time: 50 minutes

Prompt:

Build an account and transaction service. Begin with account creation and deposits. Add transfers, activity rankings, scheduled transactions, and idempotent retries incrementally.

Score:

- Correctness: 30%
- Code quality: 20%
- Communication: 20%
- Edge cases and tests: 20%
- Complexity: 10%

### Round 2: System Design

Time: 50 minutes

Prompt:

Design a credit-card authorization platform that validates transactions, enforces cumulative limits, integrates with a payment network, and operates across two AWS regions.

Score:

- Requirements and invariants: 20%
- Architecture and technology choices: 25%
- Data correctness: 20%
- Reliability and security: 20%
- Communication and trade-offs: 15%

### Round 3: Technical Case

Time: 50 minutes

Prompt:

Reusable virtual cards have reduced fraud, but false declines increased after merchants changed payment processors. Diagnose the problem, evaluate options, review a flawed merchant-matching implementation, and recommend a rollout.

Score:

- Clarifying questions: 15%
- Business reasoning: 20%
- Technical analysis: 25%
- Code/debugging quality: 20%
- Recommendation and communication: 20%

### Round 4: Behavioral

Time: 45 minutes

Questions:

1. Tell me about the most complex technical initiative you led.
2. Tell me about a time you pushed back on a risky product request.
3. Tell me about a production incident where your initial assumption was wrong.
4. How have you raised engineering standards beyond your immediate team?

Score:

- Leadership scope: 25%
- Ownership and judgment: 25%
- Results and customer impact: 20%
- Influence and collaboration: 20%
- Clarity: 10%

### Debrief

Time: 45 minutes

For each round, write:

- What went well
- Where you became unclear or slow
- One technical gap
- One communication gap
- The single highest-value correction for Day 7

### Readiness Threshold

Aim for:

- No score below 3.5 out of 5
- Overall average at least 4 out of 5
- No missed correctness invariant
- No behavioral answer without a measurable result
- No design answer without rollout and observability

## Day 7: Final Calibration And Recovery

Date: Monday, June 15

Time: 2 hours maximum

### Objectives

- Correct only the highest-value gaps.
- Rehearse openings and closings.
- Protect energy for Power Day.

### Tasks

- [ ] Redo the weakest 30-minute section from the full mock.
- [ ] Review the eight STAR story headlines and metrics.
- [ ] Rehearse one coding opening, one system-design opening, and one case opening.
- [ ] Review AWS choices and financial-system invariants.
- [ ] Prepare five thoughtful interviewer questions.
- [ ] Verify interview link, camera, microphone, editor, internet, charger, water, and breaks.
- [ ] Stop heavy preparation at least three hours before sleep.

### Rapid Review: Hard Invariants

- Do not approve from stale balance data.
- Use atomic conditional updates for cumulative limits.
- Use stable transaction IDs for idempotency.
- Persist financial state and outbox intent atomically.
- Treat caches and replicas as optimizations, not correctness authorities.
- Preserve immutable ledger history.
- Reconcile with external networks.
- Fail closed when a financial or fraud-control decision cannot be made safely.
- Use staged rollouts, guardrails, and kill switches.
- Communicate customer and business impact.

### Interviewer Questions

1. What organization-level technical problems would this Senior Lead own in the first six months?
2. How are architecture decisions made across teams, and where does a Senior Lead have decision authority?
3. How does the organization balance delivery speed with regulatory, security, and reliability requirements?
4. What distinguishes a strong Senior Lead from a strong Lead or Senior Engineer at Capital One?
5. How are Senior Leads expected to mentor engineers and influence standards beyond their immediate team?

## Compact Round Checklists

### Coding

- [ ] Restate requirements.
- [ ] Ask about invalid inputs and return behavior.
- [ ] Choose data structures aloud.
- [ ] Implement the simplest correct version.
- [ ] Test before adding requirements.
- [ ] Discuss production concurrency and persistence separately.

### System Design

- [ ] Define scope, scale, and service-level objectives.
- [ ] State hard invariants.
- [ ] Draw APIs, data, and components.
- [ ] Deep-dive into correctness and failure modes.
- [ ] Explain AWS and datastore choices.
- [ ] Cover security, observability, rollout, and cost.

### Case Study

- [ ] Clarify objective and success metrics.
- [ ] Separate evidence from assumptions.
- [ ] Think like a business owner.
- [ ] Explain reasoning before coding.
- [ ] Compare options.
- [ ] Recommend one path with guardrails.

### Behavioral

- [ ] Answer the exact question.
- [ ] Keep context short.
- [ ] Focus on personal actions and decisions.
- [ ] Quantify results.
- [ ] Explain conflict and trade-offs honestly.
- [ ] End with learning and lasting impact.

## Sources And Confidence

Official:

- [Capital One case interview guidance](https://www.capitalonecareers.com/en/4-tips-to-ace-your-capital-one-case-interview-101-students)
- [Current Senior Lead Software Engineer role example](https://www.capitalonecareers.com/en/job/mclean/senior-lead-software-engineer/1732/92962725088)
- [Current Senior Lead Full Stack role example](https://www.capitalonecareers.com/en/job/new-york/senior-lead-software-engineer-full-stack-react-scala/1732/95949035408)

Candidate-reported and therefore anecdotal:

- [Recent Senior SDE Power Day discussion](https://www.reddit.com/r/leetcode/comments/1l19a0t/capital_one_senior_sde_power_day/)
- [2026 Power Day discussion](https://www.reddit.com/r/leetcode/comments/1r28bh4/capital_one_power_day/)
- [Technical Lead interview experience](https://leetcode.com/discuss/post/5802074/)

Use public experiences to understand the skills and format, not to memorize specific questions. The recruiter-provided agenda should override this plan if it differs.
