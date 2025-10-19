# Chapter 12
# Software Design and Architecture

## Space-Based Architecture (SBA) Style

### Motivation
- Handles applications with **high spikes in user/request volume** and **high throughput** (10,000+ concurrent users).  
- Examples: Concert Ticketing Systems, Online Auction Systems.  
- Requirements: High responsiveness, scalability, and elasticity.

### Structural Characteristics
- **Foundation:** Event-Driven Architecture  
- **Overall Cost:** $$$  
- **Partitioning Type:** Technical  
- **Number of Quanta:** 1 to many  

### Technical Characteristics
| Aspect         | Rating |
|----------------|--------|
| Simplicity     | ☆☆ |
| Modularity     | ★★★★ |
| Maintainability| ★★★★ |
| Testability    | ★★★★ |
| Deployability  | ★★★★ |
| Evolvability   | ★★★ |
| Responsiveness | ★★★★★ |
| Scalability    | ★★★★★★★★★★ |
| Elasticity     | ★★★ (limited by DB operations) |
| Fault Tolerance| ★★★★ |

### Operational Characteristics
- Relies on **replicated in-memory data grids**  
- Removes the **central database** as a synchronous bottleneck  
- Uses **BASE database**: sacrifices immediate consistency for availability and scalability  
- Asynchronous data updates to DB via **messaging with persistent queues**  
- Processing units dynamically start/stop based on user load  

### Components
- **Processing Unit:** Application code + in-memory data grid + replication engine (Hazelcast, Apache Ignite, Oracle Coherence)  
- **Virtualized Middleware:**  
  - **Messaging Grid:** Manages input requests and session state  
  - **Data Grid:** Synchronizes data replication between processing units  
  - **Processing Grid:** Orchestrates request processing among units  
- **Deployment Manager:** Manages dynamic startup/shutdown of units based on load  
- **Data Pumps:** Asynchronously send updated data to DB  
- **Data Writers/Readers:** Accept messages from pumps, update or read DB  

### Data Collisions
- **Problem:** Conflicting updates across cache instances during replication  
- **Collision Rate Formula:**  
\[
\text{Collision Rate} = \frac{N \cdot UR^2}{S \cdot RL}
\]  
Where:  
- \(N\) = Number of service instances using same cache  
- \(UR\) = Update rate in ms  
- \(S\) = Cache size (rows)  
- \(RL\) = Replication latency in ms  

### Cloud vs. On-Premises
- **Cloud:** Uses cloud-based data infrastructure  
- **On-Premises:** Company owns infrastructure  
- **Both:** Use data pumps for synchronization  

### Replicated vs. Distributed Caching
| Type | Protocol | Notes |
|------|---------|------|
| Replicated | Asynchronous proprietary | SBA primary method |
| Distributed | Synchronous proprietary | Caching server required |
- **Near-Cache:** Hybrid model bridging in-memory data grid with distributed cache; front caches synced with backing cache  

### Implementation Examples
- **Concert Ticketing System:** Rapid scaling during ticket sales; seating availability updated in-memory  
- **Online Auction System:** Dynamic processing unit scaling; asynchronous data pumps handle bids  

---

## Choosing the Appropriate Architecture Style

### Shifting "Fashion" in Architecture
- Architecture trends change due to:  
  - Past observations  
  - Ecosystem and technology changes  
  - Domain evolution  
  - Acceleration rate  

### Decision Criteria
- **Domain:** Operational requirements and characteristics  
- **Architecture Characteristics:** Support needed features (e.g., scalability, fault tolerance)  
- **Data Architecture:** Impact of data design  
- **Organizational Factors:** Cloud vendor costs, infrastructure  
- **Process & Teams:** Dev, QA, Ops practices  
- **Domain/Architecture Isomorphism:** Match architecture topology to problem domain (e.g., microkernel for customizable systems)  

### Key Decisions
| Decision | Monolith | Distributed |
|----------|----------|------------|
| Single quantum | ✔ | ✖ |
| Multiple quanta | ✖ | ✔ |
| Data Location | Single/few relational DBs | Service-level persistence, decide data flow |
| Communication Style | Synchronous (simple) | Asynchronous (scale & performance benefits) |

### Design Process Output
- **Architecture Topology:** Chosen style (and hybridizations)  
- **Architecture Decisions:** Document challenging design aspects  
- **Fitness Functions:** Protect operational and design principles  

### Case Studies
- **Monolith:** Silicon Sandwiches  
  - Single quantum sufficient  
  - Modular Monolith or Microkernel for customizability  
  - Uses BFF pattern in API layer  
- **Distributed:** Going, Going, Gone  
  - Event-Driven or Microservice architecture  
  - Microservices with asynchronous communication
