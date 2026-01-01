# Werewolf — Kotlin Agentic “Fake Multiplayer” Game (CLI + JavaFX + Paper)

A multi-module Kotlin project that implements a **Werewolf/Mafia-style game engine** where you play single-player, while **15 AI-controlled personas** simulate a multiplayer lobby. These AI players “talk”, decide night actions, vote, and adapt based on the evolving chat + game state—driven via the **OpenAI API** with **strict JSON decision outputs** for deterministic orchestration.

---

## Why this exists

Real multiplayer is hard (networking, matchmaking, latency, UX, moderation). This project aims to deliver the **social deduction experience** *without* real multiplayer by using:

- **Agent personas** (role + personality + goals)
- **Shared conversation context** (game log + memory)
- **Structured decision-making** (JSON-only actions + votes)

You get the *feel* of a lobby: arguments, alliances, deception, and voting—without needing other humans online.

---

## Key Features

- **Core game engine** (rules, phases, roles, win conditions) in a platform-agnostic module
- **Agentic fake multiplayer**
    - 15 AI personas with distinct behavior profiles
    - LLM-driven discussion + reasoning
    - Independent voting and night actions
- **Deterministic orchestration**
    - JSON-only responses for decisions (actions, targets, votes)
    - Schema validation + retries on invalid output
- **Multi-platform targets**
    - **CLI** (fast iteration / debugging)
    - **JavaFX Desktop** (FXML + Controls)
    - **Minecraft Paper plugin** (Paper API; local test server via run-paper)
- **Persistence**
    - SQLite via Exposed (optional early, required later for long-term memory & analytics)
- **Asynchronous engine**
    - Coroutines for parallel agent calls + timed phases

---

## Repository Structure (Multi-Module)

- `core/`  
  **Pure game engine + LLM abstraction**, no UI dependencies.
    - Domain model: players, roles, phases, events
    - Engine loop + rules
    - Agent interfaces + prompt building
    - JSON schemas + validation
    - Persistence interfaces (optional adapter here, concrete DB module later if desired)

- `desktop/`  
  **JavaFX UI** using `core`.
    - FXML views (lobby, day chat, night actions, results)
    - State binding + rendering engine events
    - Controls for pacing (auto-play, step-by-step)

- `minecraft/`  
  **Paper plugin** using `core`.
    - Chat-based UI + commands
    - Scoreboards / bossbars for phase info
    - Integrates with Paper event loop and permissions

---

## Game Model & Flow

### Roles (initial MVP set)

- Villager
- Werewolf
- Seer (investigate)
- Doctor (protect)

(Extend later: Hunter, Witch, Bodyguard, Jester, etc.)

### Phases

1. **Lobby / Setup**
    - Create AI personas
    - Assign roles
    - Initialize memory (short-term + long-term)
2. **Night**
    - Each role that acts produces a **JSON action** (e.g., kill, protect, investigate)
    - Engine resolves actions with ordering rules
3. **Day**
    - Agents generate discussion messages
    - Player can participate (CLI input / JavaFX input / Minecraft chat)
4. **Voting**
    - Each agent returns a **JSON vote** (target + confidence + rationale)
    - Engine tallies votes, applies tie rules
5. **Elimination & Win Check**
    - Remove lynched player
    - Evaluate win conditions
6. Repeat until game ends.

---

## LLM Integration Philosophy

### MVP Choice: Direct OpenAI API

For the first working version, use **direct OpenAI API calls** with:

- explicit system prompts per persona
- JSON-only output for actions & votes
- strict validation & retries

Agent orchestration frameworks (e.g. Koog) can be layered later once the core gameplay loop is stable.

### JSON-Only Contract (Non-negotiable)

All agent “decisions” must be machine-parseable JSON:

- night actions
- votes
- (optional) suspicion updates / memory notes

Natural language is allowed for **discussion**, but decisions are strictly structured.

---

## Tech Stack

### Language & Runtime

- Kotlin (JVM)
- Java 21 toolchain

### Core Libraries

- Coroutines (`kotlinx-coroutines-core`)
- JSON parsing: Gson
- Logging: `kotlin-logging` + Logback
- Persistence: Exposed + SQLite (`sqlite-jdbc`)

### Platform Libraries

- Desktop: JavaFX (FXML + Controls)
- Minecraft: Paper API + run-paper (for local dev server)

### Testing

- JUnit Jupiter

---

## Getting Started

### Prerequisites

- JDK 21 installed
- Gradle Wrapper configured (pin to the version that matches your plugin ecosystem)
- OpenAI API key

### Environment Variables

Set your API key (example):

- Windows PowerShell:
  ```powershell
  setx OPENAI_API_KEY "your-key-here"

## Build

From repo root:

```bash
./gradlew build
```

---

## Running

### 1) CLI (core) — recommended for early iteration

You’ll have a `main()` entrypoint in `core` (or a small `cli` module if you create one later).

Example (once wired):

```bash
./gradlew :core:run
```

### 2) JavaFX Desktop

Example (once wired):

```bash
./gradlew :desktop:run
```

### 3) Minecraft (Paper)

Local dev server task depends on your Gradle/run-paper setup.

Example (once fixed/available):

```bash
./gradlew :minecraft:runServer
```

---

## Cost / Performance / Reliability Notes (LLM)

You will be calling the model multiple times per round:

* discussion messages (many)
* actions (night)
* votes (day)

### Cost controls to implement early

* message caps per day
* shorten context (summarize + structured memory)
* rate limit + backoff
* parallel calls with a concurrency limit

### Reliability controls

* JSON schema validation
* automatic retry with error feedback
* fallbacks (e.g., random valid move if agent fails repeatedly)

---

## TODO Roadmap (Detailed, Actionable, Ordered)

This is written as an engineering execution plan. Keep tasks small, testable, and version-control-friendly.

### Phase 0 — Repo & Build Stability (must be rock-solid)

* Pin Gradle wrapper to a known-good version

    * Ensure run-paper and other plugins resolve cleanly.
* Centralize Kotlin plugin version in root

    * Root: `kotlin("jvm") version "X" apply false`
    * Subprojects: `kotlin("jvm")` without version.
* Standardize module layout

    * Confirm `core/src/main/kotlin`, etc.
* Add a shared `gradle/libs.versions.toml` (optional but recommended)

    * Centralize dependency versions.
* Add consistent logging config

    * Provide `logback.xml` in relevant modules or resources.
* Verify IntelliJ Gradle import

    * “Build and run using Gradle”
    * Gradle JVM = 21
* Create minimal runnable entrypoints

    * `core`: CLI `main()`
    * `desktop`: JavaFX `Application`
    * `minecraft`: plugin main class + plugin.yml generation strategy

---

### Phase 1 — Core Domain Model (engine skeleton)

* Define core data model

    * `GameId`, `PlayerId`
    * `Role` enum/sealed class
    * `AliveState` / `PlayerState`
    * `Phase` enum (`LOBBY`, `NIGHT`, `DAY`, `VOTING`, `ENDED`)
* Define event model

    * `GameEvent` sealed class (e.g., `PhaseStarted`, `MessagePosted`, `PlayerKilled`, `PlayerVoted`)
* Define game state

    * Immutable `GameState` + reducer approach OR mutable with strict rules
* Define rules + win conditions

    * Werewolves win when parity achieved
    * Villagers win when no wolves remain
* Implement phase controller

    * `advancePhase(state): state`
* Add deterministic RNG

    * Seeded RNG for reproducible tests

**Deliverable:** Core engine can run a round with dummy inputs (no LLM yet).

---

### Phase 2 — Agent Interfaces & Orchestration Contracts

* Create `Agent` abstraction

    * `AgentId`
    * persona profile
    * role binding
* Define conversation log model

    * `ChatMessage(authorId, timestamp, content)`
* Define agent memory model

    * short-term: last N messages + key facts
    * long-term: DB-backed notes (later)
* Define structured decisions

    * `NightActionDecision`
    * `VoteDecision`
    * optional: `SuspicionUpdate`
* Define JSON schemas

    * “action type”, “target”, “confidence”, “reason”
* Add schema validation layer

    * Validate parsed Gson objects
    * Reject invalid targets, illegal actions, missing fields
* Add retry strategy

    * On invalid JSON: re-ask model with strict correction prompt
    * After N failures: fallback (random valid action)

**Deliverable:** Engine can request decisions from a stub agent implementation.

---

### Phase 3 — OpenAI Integration (MVP)

* Add `OpenAiClient` wrapper in `core` (or `core:llm` package)

    * API key from env
    * model selection config
    * request timeouts
* Implement prompt builder

    * system prompt: role + persona + objectives + constraints
    * user prompt: current phase + relevant game state + recent chat
* Implement two endpoints

    * `generateDiscussionMessage()`: free-form text
    * `generateDecisionJson()`: JSON-only
* Add request tracing

    * correlation id per game
    * per-agent request id
* Add rate limiting

    * global concurrency limit (e.g., `Semaphore`)
    * exponential backoff on 429/5xx
* Add token/context management

    * Summarize old chat into “facts”
    * Keep last N messages raw
* Add cost guardrails

    * cap total messages per day
    * cap per-agent output length
* Add safety

    * hard constraints that prevent instruction injection (players shouldn’t override system rules)

**Deliverable:** Full loop runs with real OpenAI calls for 15 agents (basic).

---

### Phase 4 — Game Loop Integration (real gameplay)

* Implement night resolution order

    * werewolf kill
    * doctor protect
    * seer investigate
    * resolve conflicts
* Implement day discussion turn-taking

    * Round-robin speakers
    * allow “player interject”
    * stop conditions (time, number of messages)
* Implement voting

    * gather votes from all alive agents
    * validate targets
    * tally and resolve ties
* Implement elimination & reveal policy

    * reveal role on death (configurable)
* Implement win check

    * after night + after vote
* Implement game summary

    * end screen summary + logs

**Deliverable:** A complete playable CLI session.

---

### Phase 5 — Persistence (SQLite + Exposed)

* Define DB schema (Exposed)

    * games
    * players
    * events
    * agent memory notes
* Add repository layer

    * `GameRepository`, `EventRepository`, `MemoryRepository`
* Persist:

    * game start/end
    * per-phase events
    * chat log
    * per-agent decisions
* Implement long-term memory

    * periodic memory snapshots (“facts”)
    * agent-specific notes: suspects, trust, alliances
* Add migrations strategy (simple versioning)

**Deliverable:** games are replayable and auditable.

---

### Phase 6 — Desktop (JavaFX)

* Create app shell

    * main window + navigation
* Screens

    * lobby (start game, agent count, roles)
    * game view (phase + players + chat)
    * night results (hidden/revealed info depending on player role)
    * voting UI (vote selection + results)
    * end screen summary
* Bind engine updates to UI

    * event bus / flow / observer
* Add pacing controls

    * autoplay vs step-by-step
    * “advance phase” button
* Error UI

    * show LLM failures + retries
* UX polish

    * highlights for suspects / votes
    * timeline of events

**Deliverable:** desktop playable with good UX.

---

### Phase 7 — Minecraft (Paper)

* Plugin bootstrap

    * `/werewolf start`, `/werewolf stop`, `/werewolf status`
* Display

    * chat-based discussion
    * bossbar/scoreboard for phase
* Input mapping

    * player messages feed into game chat
    * commands for voting
* Run loop integration

    * schedule ticks
    * do not block main thread (async LLM calls)
* Permissions + config
* Persist config

    * API key strategy (prefer env, fallback config.yml)
* Safety

    * rate limit chat spam
    * sanitize outputs

**Deliverable:** playable inside a Paper server.

---

### Phase 8 — Testing & Quality

* Unit tests for core rules

    * win conditions
    * tie resolution
    * action legality
* Deterministic integration tests

    * stub agents with scripted outputs
* JSON contract tests

    * invalid json → retry → success
* Load test

    * 15 agents, multiple rounds
* Linting/formatting (optional)

    * ktlint/detekt if you want strict hygiene

---

### Phase 9 — Future Enhancements (post-MVP)

* Add more roles + balancing
* Rich agent memory: embeddings + retrieval (optional, expensive)
* Switch to orchestration framework (Koog) after stability
* Analytics dashboard (who suspected whom, vote graphs)
* “Difficulty” presets (more/less deceptive agents)
* Partial “real multiplayer” later (network mode)

---

## Project Status

* Multi-module structure exists (`core`, `desktop`, `minecraft`)
* Focus right now: stabilize build + run CLI MVP
* Desktop/Minecraft are platform layers and should not block engine progress

---

## Disclaimer

This project uses external LLM services which may:

* incur costs
* introduce latency
* produce unexpected outputs

The game is experimental. Always enforce JSON schemas and never trust model output blindly.
