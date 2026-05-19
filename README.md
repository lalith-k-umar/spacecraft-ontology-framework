# ONYXIS  
### Ontology-Driven Satellite Intelligence & Fault Reasoning System

ONYXIS is an ontology-driven intelligent satellite health monitoring and fault diagnosis system that combines semantic web technologies, telemetry simulation, and SWRL-based reasoning to model and monitor spacecraft subsystems in real time.

The project demonstrates how ontology engineering and knowledge graph reasoning can be applied to aerospace systems for:

- semantic subsystem modeling,
- telemetry-driven fault inference,
- explainable reasoning,
- causal subsystem degradation analysis,
- and spacecraft health monitoring.

---

# 🚀 Features

- 🛰️ Ontology-driven spacecraft system modeling
- 📡 Telemetry-based fault inference using SWRL
- 🧠 Semantic reasoning using Pellet Reasoner
- 🔍 Explainable subsystem diagnostics
- ⚡ Dynamic telemetry simulation
- 🔄 Scenario-based telemetry variation
- 🛡️ Autonomous operational response modeling
- 🔗 Controlled causal dependency reasoning
- 📊 Multi-subsystem spacecraft monitoring

---

# 🧩 Spacecraft Subsystems Modeled

## TT&C (Telemetry, Tracking & Command)

- TTCAntenna
- TTCRadio
- Encoder / Decoder
- Upconverter / Downconverter
- Duplexer
- GroundLink
- CommandReceiver

---

## ADCS (Attitude Determination & Control System)

- StarTracker
- SunSensor
- EarthSensor
- Gyroscope
- Magnetometer
- Magnetorquer
- ReactionWheel
- TrackingUnit
- AttitudeControl

---

## Power Subsystem

- Battery
- SolarPanel
- SolarArray
- PCDU
- VoltageRegulator
- ChargeController
- PowerBusLine

---

## Thermal Subsystem

- Heater
- Thermal telemetry monitoring
- Overheat / Underheat reasoning

---

## Propulsion Subsystem

- Thruster
- OrbitControl
- Propulsion thermal reasoning

---

# 🧠 Ontology Reasoning Architecture

```text
Telemetry Generation
        ↓
Scenario-Based Telemetry Variation
        ↓
Ontology Individual Update
        ↓
Pellet Reasoning
        ↓
SWRL Rule Execution
        ↓
Fault / Degradation Inference
        ↓
Operational Response
        ↓
Visualization & Monitoring
```

---

# 🔄 Dynamic Telemetry & Scenario Engine

ONYXIS uses dynamic telemetry simulation rather than static threshold testing.

The system continuously generates telemetry values across multiple spacecraft subsystems and updates ontology individuals in real time.

To improve demonstration quality and reasoning visibility:

- telemetry values vary dynamically,
- subsystem conditions evolve continuously,
- inferred faults change over time,
- and operational responses adapt accordingly.

---

# 📡 Telemetry Cycle & Inference Flow

The system follows a continuous telemetry reasoning loop.

## Telemetry Update Process

- Telemetry values are generated continuously.
- Multiple telemetry cycles are executed before reasoning.
- After approximately **8 telemetry update loops**, ontology reasoning is triggered.
- Pellet executes SWRL reasoning rules.
- Newly inferred faults, degradations, and actions are displayed.

This creates realistic subsystem evolution instead of static repeated inference.

---

# 🎯 Scenario-Based Telemetry Simulation

The telemetry engine dynamically switches between operational spacecraft scenarios to demonstrate changing ontology inference behavior.

Examples include:

| Scenario | Effect |
|---|---|
| Normal Operation | Healthy telemetry |
| TT&C Failure | Weak signal & communication degradation |
| Thermal Emergency | High subsystem temperatures |
| Power Failure | Battery drain & power instability |
| ADCS Instability | Pointing error & tracking degradation |
| Propulsion Failure | Low thrust & orbit deviation |
| Sensor Degradation | Accuracy deterioration |

This allows the ontology to infer different subsystem faults dynamically during runtime.

---

# ⚙️ Technologies Used

| Technology | Purpose |
|---|---|
| OWL | Ontology Modeling |
| SWRL | Semantic Rule Reasoning |
| Pellet | Ontology Reasoner |
| Protégé | Ontology Development |
| Python | Telemetry Simulation |
| RDF/XML | Ontology Representation |
| Semantic Web Stack | Knowledge Representation |

---

# 🛰️ Semantic Reasoning Features

## Telemetry-Driven Fault Detection

Example:

```text
Battery Temperature > 50°C
        ↓
BatteryOverheatFault
        ↓
Severity = HIGH
        ↓
SafeMode Triggered
```

---

## Controlled Degradation Semantics

ONYXIS avoids uncontrolled same-fault propagation across all subsystems.

Instead, the ontology uses controlled degradation concepts such as:

- CommunicationDegradation
- ReducedPerformance
- PowerInstability

This produces more realistic spacecraft subsystem behavior.

---

# 🔥 SWRL Reasoning Capabilities

The ontology currently includes:

- ~64 SWRL rules
- telemetry threshold reasoning
- subsystem-specific fault inference
- operational response rules
- severity-based actions
- controlled causal propagation
- degradation-oriented reasoning

---

# 🛡️ Operational Actions

ONYXIS supports autonomous operational responses including:

| Severity | Action |
|---|---|
| LOW | Warning |
| MEDIUM | PowerSaveMode |
| HIGH | SafeMode |

Additional actions include:

- HeaterON
- Deployment warnings
- Structural protection behavior

---

# 📊 Example Fault Scenarios

## Thermal Emergency

```text
BatteryTemp = 65°C
OBC Temp = 92°C
```

Inferred:

- BatteryOverheatFault
- OBCOverheatFault
- SafeMode activation

---

## TT&C Failure

```text
SignalStrength = 12
TransmitPower = 3
```

Inferred:

- TTCRadioFault
- CommunicationDegradation
- GroundLinkFault

---

## ADCS Instability

```text
TrackingAccuracy = 0.4
PointingError = 6
```

Inferred:

- TrackingFault
- AntennaPointingFault
- ReducedPerformance

---

# ⚙️ Installation

## Clone Repository

```bash
git clone <YOUR_REPOSITORY_URL>
cd ONYXIS
```

---

---

## Activate Environmen

---

## Install Dependencies

```bash
pip install -r requirements.txt
```

---



---


## Run backend

```bash
#  python -m uvicorn backend.ontology_service:app --host 0.0.0.0 --port 8000 --reload  
```

---

## Run Dashboard / UI

```bash
# npm run dev 
```

---

# 📈 Current Ontology Statistics

| Component | Approx Count |
|---|---|
| Classes | 130+ |
| Individuals | 700+ |
| Object Properties | 30+ |
| Datatype Properties | 45+ |
| SWRL Rules | ~64 |

---

# 🎯 Research & Prototype Goals

ONYXIS demonstrates:

- ontology-driven spacecraft monitoring,
- semantic telemetry reasoning,
- explainable AI for aerospace systems,
- subsystem-aware fault diagnosis,
- dynamic ontology inference,
- and intelligent knowledge graph applications in space systems.

---

# 🔮 Future Enhancements

Possible future extensions include:

- SHACL validation constraints
- Temporal reasoning
- Real-time dashboards
- Advanced causal dependency modeling
- Stream reasoning
- Digital twin integration

---

# 👨‍💻 Author

**M.Lalith Kumar
G.Viswesh
K.Reshma**

---

# 📜 License

This project is intended for:
- educational purposes,
- research demonstrations,
- semantic systems prototyping,
- and aerospace knowledge graph experimentation.
