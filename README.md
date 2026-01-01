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
