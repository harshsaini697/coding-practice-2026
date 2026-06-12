# Capital One Senior Lead Engineer - Interview Cheat Sheet

## System Design Principles

| Topic                | Key Takeaway                                                         |
| -------------------- | -------------------------------------------------------------------- |
| Idempotency          | Prevent duplicate transaction processing during retries.             |
| Reconciliation       | Detect and repair inconsistencies across systems.                    |
| Outbox Pattern       | Persist event and business transaction atomically before publishing. |
| Saga Pattern         | Use compensating transactions instead of distributed 2PC.            |
| Event Sourcing       | Store events as source of truth; state derived from events.          |
| CQRS                 | Separate read and write models for scalability.                      |
| DLQ                  | Store failed messages for investigation and replay.                  |
| CAP Theorem          | Banking systems typically prioritize Consistency over Availability.  |
| Strong Consistency   | Preferred for balances, payments, and ledger systems.                |
| Eventual Consistency | Acceptable for notifications and reporting systems.                  |

---

## Banking Concepts

| Concept         | Explanation                                   |
| --------------- | --------------------------------------------- |
| Authorization   | Verify whether transaction can proceed.       |
| Capture         | Commit authorized funds.                      |
| Settlement      | Actual movement of money between parties.     |
| Ledger          | Immutable source of truth for money movement. |
| Chargeback      | Customer disputes a transaction.              |
| AML             | Anti-Money Laundering controls.               |
| Fraud Detection | Real-time risk evaluation before approval.    |
| Audit Trail     | Record who did what and when.                 |

---

## AWS Architecture

| Service     | When to Use                         | Tradeoff                    |
| ----------- | ----------------------------------- | --------------------------- |
| API Gateway | Authentication, throttling, routing | Extra network hop           |
| ALB         | Traffic distribution                | No API management           |
| SQS FIFO    | Ordered processing                  | Lower throughput            |
| SNS         | Fan-out events                      | No ordering guarantees      |
| DynamoDB    | Massive scale, key-value access     | Limited joins               |
| Aurora      | ACID transactions, SQL              | More expensive              |
| ECS         | Container workloads                 | More operational management |
| Lambda      | Event-driven workloads              | Cold starts                 |
| CloudWatch  | Metrics, logging, monitoring        | Cost at scale               |
| WAF         | Security protection                 | Additional configuration    |

---

## API Gateway Topics

Know how to discuss:

* Authentication (JWT/OAuth)
* Authorization
* TLS/HTTPS
* Rate limiting
* Request validation
* Routing
* Monitoring
* Logging
* WAF
* Versioning
* Timeouts
* Idempotency
* Stage deployments

---

## SQL vs NoSQL

| SQL                | NoSQL                 |
| ------------------ | --------------------- |
| Normalized         | Denormalized          |
| Joins supported    | Avoid joins           |
| Strong consistency | Scale-focused         |
| ACID               | Flexible consistency  |
| Relational model   | Access-pattern driven |

### Why Normalize?

* Reduce redundancy
* Avoid update anomalies
* Avoid delete anomalies
* Improve data integrity
* Enforce relationships via foreign keys

### How NoSQL Replaces Joins?

* Denormalization
* Embedded documents
* Materialized views
* Application-side aggregation
* Event-driven read models

---

## Consensus Algorithms

### Raft

* Leader-based consensus
* Followers replicate logs
* Majority commits
* Easier to understand and operate

### Paxos

* More complex consensus algorithm
* Used by systems like Spanner
* Harder to implement and explain

### Interview Answer

"Consensus algorithms ensure distributed replicas agree on a single state despite failures."

---

## SOLID Principles

| Principle | Meaning               |
| --------- | --------------------- |
| S         | Single Responsibility |
| O         | Open/Closed           |
| L         | Liskov Substitution   |
| I         | Interface Segregation |
| D         | Dependency Inversion  |

---

## AI / Agentic AI Stories

### Incident Management Summaries

Problem:

* Manual incident writeups

Solution:

* AI-generated summaries from ICM data and communications

Impact:

* Reduced operational overhead
* Standardized documentation

---

### Roslyn Analyzer

Problem:

* Bugs discovered late

Solution:

* Static analysis and code-quality automation

Impact:

* Shift-left quality
* Faster feedback

---

### Deterministic Orchestration Validation

Problem:

* Workflow trees must remain deterministic

Solution:

* Detect orchestration tree changes
* Version-gated rollout
* Request stamping

Impact:

* Safe deployments
* Reliable workflow execution

---

## Medical Recommendation System Paper

### Original Design

* Rule-based expert system
* Bayesian probability engine
* Medical data crawler
* Symptom-based recommendations

### Modern Improvements

* RAG using verified medical sources
* LLM-assisted recommendations
* Agentic workflow architecture
* Human-in-the-loop validation
* Explainability and confidence scoring
* Auditability and governance

### Tradeoffs

| Topic                        | Tradeoff                              |
| ---------------------------- | ------------------------------------- |
| Rules vs ML                  | Explainability vs learning capability |
| Bayes vs Deep Learning       | Simplicity vs model complexity        |
| Precision vs Recall          | False positives vs false negatives    |
| Human-in-loop vs Automation  | Safety vs speed                       |
| Curated Data vs Crawled Data | Quality vs coverage                   |

---

## Capital One Power Day Focus Areas

### System Design

Prepare:

* Payment processing system
* Fraud detection platform
* Event-driven architecture
* Customer profile platform
* Notification system

### Leadership

Prepare STAR stories for:

* Technical disagreement
* Mentoring engineers
* Influencing architecture
* Handling production incidents
* Driving organizational change

### Common Deep-Dive Questions

* Why API Gateway?
* Why Kafka instead of SQS?
* Why DynamoDB instead of Aurora?
* What happens if a service crashes?
* How do you prevent duplicate transactions?
* How do you recover from event publication failures?

### Senior Lead Mindset

Always discuss:

* Tradeoffs
* Scalability
* Reliability
* Compliance
* Auditability
* Failure handling
* Operational excellence
