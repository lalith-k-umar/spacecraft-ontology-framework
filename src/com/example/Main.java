package com.example;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.formats.RDFXMLDocumentFormat;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.SWRLAtom;
import org.semanticweb.owlapi.model.SWRLBuiltInAtom;
import org.semanticweb.owlapi.model.SWRLClassAtom;
import org.semanticweb.owlapi.model.SWRLDArgument;
import org.semanticweb.owlapi.model.SWRLDataPropertyAtom;
import org.semanticweb.owlapi.model.SWRLIndividualArgument;
import org.semanticweb.owlapi.model.SWRLObjectPropertyAtom;
import org.semanticweb.owlapi.model.SWRLRule;
import org.semanticweb.owlapi.model.SWRLVariable;
import org.semanticweb.owlapi.model.SWRLLiteralArgument;
import org.semanticweb.owlapi.vocab.OWL2Datatype;
import org.semanticweb.owlapi.vocab.SWRLBuiltInsVocabulary;

public class Main {

    private static final String BASE_IRI = "http://example.org/satellite#";

    public static void main(String[] args) throws OWLOntologyCreationException, OWLOntologyStorageException, IOException {
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLDataFactory factory = manager.getOWLDataFactory();
        OWLOntology ontology = manager.createOntology(IRI.create(BASE_IRI));

        // --- CORE CLASSES ---
        OWLClass subsystem = createClass(factory, ontology, manager, "Subsystem");
        OWLClass component = createClass(factory, ontology, manager, "Component");
        OWLClass fault = createClass(factory, ontology, manager, "Fault");
        OWLClass action = createClass(factory, ontology, manager, "Action");
        OWLClass groundStationClass = createClass(factory, ontology, manager, "GroundStation");

        // --- SUBSYSTEMS ---
        OWLClass aocs = createClass(factory, ontology, manager, "AOCS");
        OWLClass powerSystem = createClass(factory, ontology, manager, "PowerSystem");
        OWLClass communicationSystem = createClass(factory, ontology, manager, "CommunicationSystem");
        OWLClass thermalSystem = createClass(factory, ontology, manager, "ThermalSystem");
        OWLClass obcClass = createClass(factory, ontology, manager, "OBC");
        addSubClassAxiom(factory, ontology, manager, aocs, subsystem);
        addSubClassAxiom(factory, ontology, manager, powerSystem, subsystem);
        addSubClassAxiom(factory, ontology, manager, communicationSystem, subsystem);
        addSubClassAxiom(factory, ontology, manager, thermalSystem, subsystem);
        addSubClassAxiom(factory, ontology, manager, obcClass, subsystem);

        // --- COMPONENT HIERARCHY ---
        OWLClass sensor = createClass(factory, ontology, manager, "Sensor");
        OWLClass actuator = createClass(factory, ontology, manager, "Actuator");
        OWLClass control = createClass(factory, ontology, manager, "Control");
        OWLClass powerComponent = createClass(factory, ontology, manager, "Power_Component");
        OWLClass communicationComponent = createClass(factory, ontology, manager, "Communication_Component");
        OWLClass thermalComponent = createClass(factory, ontology, manager, "Thermal_Component");
        OWLClass obcComponent = createClass(factory, ontology, manager, "OBC_Component");
        addSubClassAxiom(factory, ontology, manager, sensor, component);
        addSubClassAxiom(factory, ontology, manager, actuator, component);
        addSubClassAxiom(factory, ontology, manager, control, component);
        addSubClassAxiom(factory, ontology, manager, powerComponent, component);
        addSubClassAxiom(factory, ontology, manager, communicationComponent, component);
        addSubClassAxiom(factory, ontology, manager, thermalComponent, component);
        addSubClassAxiom(factory, ontology, manager, obcComponent, component);

        // --- SPECIFIC CLASSES (AOCS) ---
        OWLClass gyroscope = createClass(factory, ontology, manager, "Gyroscope");
        OWLClass starTracker = createClass(factory, ontology, manager, "StarTracker");
        OWLClass sunSensor = createClass(factory, ontology, manager, "SunSensor");
        OWLClass magnetometer = createClass(factory, ontology, manager, "Magnetometer");
        OWLClass earthSensor = createClass(factory, ontology, manager, "EarthSensor");
        OWLClass gnssReceiver = createClass(factory, ontology, manager, "GNSSReceiver");
        OWLClass reactionWheel = createClass(factory, ontology, manager, "ReactionWheel");
        OWLClass magnetorquer = createClass(factory, ontology, manager, "Magnetorquer");
        OWLClass thruster = createClass(factory, ontology, manager, "Thruster");
        OWLClass attitudeControl = createClass(factory, ontology, manager, "AttitudeControl");
        OWLClass orbitControl = createClass(factory, ontology, manager, "OrbitControl");
        OWLClass antennaPointing = createClass(factory, ontology, manager, "AntennaPointing");
        OWLClass batteryClass = createClass(factory, ontology, manager, "Battery");
        OWLClass transmitter = createClass(factory, ontology, manager, "Transmitter");
        OWLClass thermalSensor = createClass(factory, ontology, manager, "ThermalSensor");
        OWLClass onBoardComputer = createClass(factory, ontology, manager, "OnBoardComputer");

        addSubClassAxiom(factory, ontology, manager, gyroscope, sensor);
        addSubClassAxiom(factory, ontology, manager, starTracker, sensor);
        addSubClassAxiom(factory, ontology, manager, sunSensor, sensor);
        addSubClassAxiom(factory, ontology, manager, magnetometer, sensor);
        addSubClassAxiom(factory, ontology, manager, earthSensor, sensor);
        addSubClassAxiom(factory, ontology, manager, gnssReceiver, sensor);
        addSubClassAxiom(factory, ontology, manager, reactionWheel, actuator);
        addSubClassAxiom(factory, ontology, manager, magnetorquer, actuator);
        addSubClassAxiom(factory, ontology, manager, thruster, actuator);
        addSubClassAxiom(factory, ontology, manager, attitudeControl, control);
        addSubClassAxiom(factory, ontology, manager, orbitControl, control);
        addSubClassAxiom(factory, ontology, manager, antennaPointing, control);
        addSubClassAxiom(factory, ontology, manager, batteryClass, powerComponent);
        addSubClassAxiom(factory, ontology, manager, transmitter, communicationComponent);
        addSubClassAxiom(factory, ontology, manager, thermalSensor, thermalComponent);
                addSubClassAxiom(factory, ontology, manager, onBoardComputer, obcComponent);

        // --- COMMUNICATION CHAIN CLASSES ---
        OWLClass lnaClass = createClass(factory, ontology, manager, "LNA");
        OWLClass filterClass = createClass(factory, ontology, manager, "Filter");
        OWLClass downconverterClass = createClass(factory, ontology, manager, "Downconverter");
        OWLClass demodulatorClass = createClass(factory, ontology, manager, "Demodulator");
        OWLClass encoderClass = createClass(factory, ontology, manager, "Encoder");
        OWLClass upconverterClass = createClass(factory, ontology, manager, "Upconverter");
        OWLClass powerAmpClass = createClass(factory, ontology, manager, "PowerAmplifier");
        OWLClass loClass = createClass(factory, ontology, manager, "LocalOscillator");
        OWLClass duplexerClass = createClass(factory, ontology, manager, "Duplexer");
        OWLClass antennaClass = createClass(factory, ontology, manager, "Antenna");

        addSubClassAxiom(factory, ontology, manager, lnaClass, communicationComponent);
        addSubClassAxiom(factory, ontology, manager, filterClass, communicationComponent);
        addSubClassAxiom(factory, ontology, manager, downconverterClass, communicationComponent);
        addSubClassAxiom(factory, ontology, manager, demodulatorClass, communicationComponent);
        addSubClassAxiom(factory, ontology, manager, encoderClass, communicationComponent);
        addSubClassAxiom(factory, ontology, manager, upconverterClass, communicationComponent);
        addSubClassAxiom(factory, ontology, manager, powerAmpClass, communicationComponent);
        addSubClassAxiom(factory, ontology, manager, loClass, communicationComponent);
        addSubClassAxiom(factory, ontology, manager, duplexerClass, communicationComponent);
        addSubClassAxiom(factory, ontology, manager, antennaClass, communicationComponent);

        // --- TT&C CLASSES ---
        OWLClass commandReceiverClass = createClass(factory, ontology, manager, "CommandReceiver");
        OWLClass decoderClass = createClass(factory, ontology, manager, "Decoder");
        OWLClass trackingUnitClass = createClass(factory, ontology, manager, "TrackingUnit");
        OWLClass telemetryEncoderClass = createClass(factory, ontology, manager, "TelemetryEncoder");
        OWLClass ttcRadioClass = createClass(factory, ontology, manager, "TTCRadio");
        OWLClass ttcAntennaClass = createClass(factory, ontology, manager, "TTCAntenna");

        addSubClassAxiom(factory, ontology, manager, commandReceiverClass, communicationComponent);
        addSubClassAxiom(factory, ontology, manager, decoderClass, communicationComponent);
        addSubClassAxiom(factory, ontology, manager, trackingUnitClass, communicationComponent);
        addSubClassAxiom(factory, ontology, manager, telemetryEncoderClass, communicationComponent);
        addSubClassAxiom(factory, ontology, manager, ttcRadioClass, communicationComponent);
        addSubClassAxiom(factory, ontology, manager, ttcAntennaClass, communicationComponent);


        // --- SPECIFIC CLASSES (POWER) ---
        OWLClass solarCellClass = createClass(factory, ontology, manager, "SolarCell");
        OWLClass solarPanelClass = createClass(factory, ontology, manager, "SolarPanel");
        OWLClass solarArrayClass = createClass(factory, ontology, manager, "SolarArray");
        OWLClass bccClass = createClass(factory, ontology, manager, "BatteryChargeController");
        OWLClass pcduClass = createClass(factory, ontology, manager, "PCDU");
        OWLClass busLineClass = createClass(factory, ontology, manager, "PowerBusLine");
        OWLClass voltRegClass = createClass(factory, ontology, manager, "VoltageRegulator");

        addSubClassAxiom(factory, ontology, manager, solarCellClass, powerComponent);
        addSubClassAxiom(factory, ontology, manager, solarPanelClass, powerComponent);
        addSubClassAxiom(factory, ontology, manager, solarArrayClass, powerComponent);
        addSubClassAxiom(factory, ontology, manager, bccClass, powerComponent);
        addSubClassAxiom(factory, ontology, manager, pcduClass, powerComponent);
        addSubClassAxiom(factory, ontology, manager, busLineClass, powerComponent);
        addSubClassAxiom(factory, ontology, manager, voltRegClass, powerComponent);

        // --- THERMAL CLASSES ---
        OWLClass mliLayerClass = createClass(factory, ontology, manager, "MLILayer");
        OWLClass coatingClass = createClass(factory, ontology, manager, "Coating");
        OWLClass radiatorClass = createClass(factory, ontology, manager, "Radiator");
        OWLClass thermalPathClass = createClass(factory, ontology, manager, "ThermalPath");
        OWLClass tempSensorClass = createClass(factory, ontology, manager, "TempSensor");
        OWLClass heaterClass = createClass(factory, ontology, manager, "Heater");
        OWLClass thermostatClass = createClass(factory, ontology, manager, "Thermostat");

        addSubClassAxiom(factory, ontology, manager, mliLayerClass, thermalComponent);
        addSubClassAxiom(factory, ontology, manager, coatingClass, thermalComponent);
        addSubClassAxiom(factory, ontology, manager, radiatorClass, thermalComponent);
        addSubClassAxiom(factory, ontology, manager, thermalPathClass, thermalComponent);
        addSubClassAxiom(factory, ontology, manager, tempSensorClass, sensor);
        addSubClassAxiom(factory, ontology, manager, heaterClass, actuator);
        addSubClassAxiom(factory, ontology, manager, thermostatClass, control);

        // --- STRUCTURE CLASSES ---
        OWLClass structureSubsystem = createClass(factory, ontology, manager, "StructureSubsystem");
        addSubClassAxiom(factory, ontology, manager, structureSubsystem, subsystem);

        OWLClass satelliteFrame = createClass(factory, ontology, manager, "SatelliteFrame");
        OWLClass panel = createClass(factory, ontology, manager, "Panel");
        OWLClass bracket = createClass(factory, ontology, manager, "Bracket");
        OWLClass mount = createClass(factory, ontology, manager, "Mount");
        OWLClass hinge = createClass(factory, ontology, manager, "Hinge");
        OWLClass deploymentMechanism = createClass(factory, ontology, manager, "DeploymentMechanism");
        OWLClass vibrationIsolation = createClass(factory, ontology, manager, "VibrationIsolation");
        OWLClass antennaMountClass = createClass(factory, ontology, manager, "AntennaMount");
        OWLClass solarPanelMountClass = createClass(factory, ontology, manager, "SolarPanelMount");
        OWLClass deploymentSwitch = createClass(factory, ontology, manager, "DeploymentSwitch");
        OWLClass vibrationSensorClass = createClass(factory, ontology, manager, "VibrationSensor");
        OWLClass positionFeedback = createClass(factory, ontology, manager, "PositionFeedback");

        OWLClass structureComponent = createClass(factory, ontology, manager, "StructureComponent");
        addSubClassAxiom(factory, ontology, manager, structureComponent, component);

        addSubClassAxiom(factory, ontology, manager, satelliteFrame, structureComponent);
        addSubClassAxiom(factory, ontology, manager, panel, structureComponent);
        addSubClassAxiom(factory, ontology, manager, bracket, structureComponent);
        addSubClassAxiom(factory, ontology, manager, mount, structureComponent);
        addSubClassAxiom(factory, ontology, manager, hinge, structureComponent);
        addSubClassAxiom(factory, ontology, manager, deploymentMechanism, actuator);
        addSubClassAxiom(factory, ontology, manager, vibrationIsolation, structureComponent);
        addSubClassAxiom(factory, ontology, manager, antennaMountClass, structureComponent);
        addSubClassAxiom(factory, ontology, manager, solarPanelMountClass, structureComponent);
        addSubClassAxiom(factory, ontology, manager, deploymentSwitch, sensor);
        addSubClassAxiom(factory, ontology, manager, vibrationSensorClass, sensor);
        addSubClassAxiom(factory, ontology, manager, positionFeedback, sensor);

        // Monitoring Points
        OWLClass batteryTempClass = createClass(factory, ontology, manager, "BatteryTemp");
        OWLClass obcTempClass = createClass(factory, ontology, manager, "OBCTemp");
        OWLClass rfTempClass = createClass(factory, ontology, manager, "RFTemp");
        OWLClass solarPanelTempClass = createClass(factory, ontology, manager, "SolarPanelTemp");
        addSubClassAxiom(factory, ontology, manager, batteryTempClass, tempSensorClass);
        addSubClassAxiom(factory, ontology, manager, obcTempClass, tempSensorClass);
        addSubClassAxiom(factory, ontology, manager, rfTempClass, tempSensorClass);
        addSubClassAxiom(factory, ontology, manager, solarPanelTempClass, tempSensorClass);

        // --- FAULT CLASSES ---
        OWLClass gyroFault = createClass(factory, ontology, manager, "GyroFault");
        OWLClass gnssFault = createClass(factory, ontology, manager, "GNSSFault");
        OWLClass starTrackerFault = createClass(factory, ontology, manager, "StarTrackerFault");
        OWLClass sunSensorFault = createClass(factory, ontology, manager, "SunSensorFault");
        OWLClass magnetometerFault = createClass(factory, ontology, manager, "MagnetometerFault");
        OWLClass earthSensorFault = createClass(factory, ontology, manager, "EarthSensorFault");
        OWLClass reactionWheelFault = createClass(factory, ontology, manager, "ReactionWheelFault");
        OWLClass thrusterFault = createClass(factory, ontology, manager, "ThrusterFault");
        OWLClass magnetorquerFault = createClass(factory, ontology, manager, "MagnetorquerFault");
        OWLClass attitudeControlFault = createClass(factory, ontology, manager, "AttitudeControlFault");
        OWLClass orbitControlFault = createClass(factory, ontology, manager, "OrbitControlFault");
        OWLClass antennaPointingFault = createClass(factory, ontology, manager, "AntennaPointingFault");
        OWLClass batteryFaultClass = createClass(factory, ontology, manager, "BatteryFault");
        OWLClass signalLossFault = createClass(factory, ontology, manager, "SignalLossFault");
        OWLClass overheatFault = createClass(factory, ontology, manager, "OverheatFault");

        addSubClassAxiom(factory, ontology, manager, gyroFault, fault);
        addSubClassAxiom(factory, ontology, manager, gnssFault, fault);
        addSubClassAxiom(factory, ontology, manager, starTrackerFault, fault);
        addSubClassAxiom(factory, ontology, manager, sunSensorFault, fault);
        addSubClassAxiom(factory, ontology, manager, magnetometerFault, fault);
        addSubClassAxiom(factory, ontology, manager, earthSensorFault, fault);
        addSubClassAxiom(factory, ontology, manager, reactionWheelFault, fault);
        addSubClassAxiom(factory, ontology, manager, thrusterFault, fault);
        addSubClassAxiom(factory, ontology, manager, magnetorquerFault, fault);
        addSubClassAxiom(factory, ontology, manager, attitudeControlFault, fault);
        addSubClassAxiom(factory, ontology, manager, orbitControlFault, fault);
        addSubClassAxiom(factory, ontology, manager, antennaPointingFault, fault);
        addSubClassAxiom(factory, ontology, manager, batteryFaultClass, fault);
        addSubClassAxiom(factory, ontology, manager, signalLossFault, fault);
        addSubClassAxiom(factory, ontology, manager, overheatFault, fault);

        OWLClass solarFault = createClass(factory, ontology, manager, "SolarFault");
        OWLClass bccFault = createClass(factory, ontology, manager, "ChargeControllerFault");
        OWLClass pcduFault = createClass(factory, ontology, manager, "PCDUFault");
        OWLClass busFault = createClass(factory, ontology, manager, "BusFault");
        OWLClass regFault = createClass(factory, ontology, manager, "RegulatorFault");

        addSubClassAxiom(factory, ontology, manager, solarFault, fault);
        addSubClassAxiom(factory, ontology, manager, bccFault, fault);
        addSubClassAxiom(factory, ontology, manager, pcduFault, fault);
        addSubClassAxiom(factory, ontology, manager, busFault, fault);
        addSubClassAxiom(factory, ontology, manager, regFault, fault);

        // Thermal Faults
        OWLClass underheatFault = createClass(factory, ontology, manager, "UnderheatFault");
        OWLClass sensorFault = createClass(factory, ontology, manager, "SensorFault");
        OWLClass heaterFaultClass = createClass(factory, ontology, manager, "HeaterFault");
        addSubClassAxiom(factory, ontology, manager, underheatFault, fault);
        addSubClassAxiom(factory, ontology, manager, sensorFault, fault);
        addSubClassAxiom(factory, ontology, manager, heaterFaultClass, fault);

        // --- STRUCTURE FAULT CLASSES ---
        OWLClass structuralFailure = createClass(factory, ontology, manager, "StructuralFailure");
        OWLClass deploymentFailure = createClass(factory, ontology, manager, "DeploymentFailure");
        OWLClass vibrationFailure = createClass(factory, ontology, manager, "VibrationFailure");
        OWLClass mountFailureClass = createClass(factory, ontology, manager, "MountFailure");
        OWLClass alignmentFailure = createClass(factory, ontology, manager, "AlignmentFailure");

        addSubClassAxiom(factory, ontology, manager, structuralFailure, fault);
        addSubClassAxiom(factory, ontology, manager, deploymentFailure, fault);
        addSubClassAxiom(factory, ontology, manager, vibrationFailure, fault);
        addSubClassAxiom(factory, ontology, manager, mountFailureClass, fault);
        addSubClassAxiom(factory, ontology, manager, alignmentFailure, fault);

        // --- COMMUNICATION FAULTS ---
        OWLClass lnaFault = createClass(factory, ontology, manager, "LNAFault");
        OWLClass filterFault = createClass(factory, ontology, manager, "FilterFault");
        OWLClass downconverterFault = createClass(factory, ontology, manager, "DownconverterFault");
        OWLClass demodulatorFault = createClass(factory, ontology, manager, "DemodulatorFault");
        OWLClass encoderFault = createClass(factory, ontology, manager, "EncoderFault");
        OWLClass upconverterFault = createClass(factory, ontology, manager, "UpconverterFault");
        OWLClass amplifierFault = createClass(factory, ontology, manager, "AmplifierFault");
        OWLClass loFault = createClass(factory, ontology, manager, "LOFault");
        OWLClass duplexerFault = createClass(factory, ontology, manager, "DuplexerFault");
        OWLClass antennaFault = createClass(factory, ontology, manager, "AntennaFault");

        addSubClassAxiom(factory, ontology, manager, lnaFault, fault);
        addSubClassAxiom(factory, ontology, manager, filterFault, fault);
        addSubClassAxiom(factory, ontology, manager, downconverterFault, fault);
        addSubClassAxiom(factory, ontology, manager, demodulatorFault, fault);
        addSubClassAxiom(factory, ontology, manager, encoderFault, fault);
        addSubClassAxiom(factory, ontology, manager, upconverterFault, fault);
        addSubClassAxiom(factory, ontology, manager, amplifierFault, fault);
        addSubClassAxiom(factory, ontology, manager, loFault, fault);
        addSubClassAxiom(factory, ontology, manager, duplexerFault, fault);
        addSubClassAxiom(factory, ontology, manager, antennaFault, fault);


        // --- HEALTH & STATUS CLASSES ---
        OWLClass healthyComponent = createClass(factory, ontology, manager, "HealthyComponent");
        OWLClass criticalPowerFailure = createClass(factory, ontology, manager, "CriticalPowerFailure");
        addSubClassAxiom(factory, ontology, manager, healthyComponent, component);
        addSubClassAxiom(factory, ontology, manager, criticalPowerFailure, fault);

        // --- OBJECT PROPERTIES ---
        OWLObjectProperty hasFault = createObjectProperty(factory, ontology, manager, "hasFault", component, fault);
        OWLObjectProperty affectsComponent = createObjectProperty(factory, ontology, manager, "affectsComponent", fault, component);
        OWLObjectProperty belongsToSubsystem = createObjectProperty(factory, ontology, manager, "belongsToSubsystem", component, subsystem);
        OWLObjectProperty propagatesTo = createObjectProperty(factory, ontology, manager, "propagatesTo", fault, fault);
        OWLObjectProperty triggersAction = createObjectProperty(factory, ontology, manager, "triggersAction", fault, action);
        OWLObjectProperty monitors = createObjectProperty(factory, ontology, manager, "monitors", obcComponent, component);
        OWLObjectProperty receivesTelemetryFrom = createObjectProperty(factory, ontology, manager, "receivesTelemetryFrom", obcComponent, component);
        OWLObjectProperty controls = createObjectProperty(factory, ontology, manager, "controls", obcComponent, actuator);

        OWLObjectProperty feedsDataTo = createObjectProperty(factory, ontology, manager, "feedsDataTo", component, component);
        OWLObjectProperty usesSensorDataFrom = createObjectProperty(factory, ontology, manager, "usesSensorDataFrom", component, component);
        OWLObjectProperty sendsCommandTo = createObjectProperty(factory, ontology, manager, "sendsCommandTo", component, component);
        OWLObjectProperty executesCommandFrom = createObjectProperty(factory, ontology, manager, "executesCommandFrom", component, component);
        OWLObjectProperty influences = createObjectProperty(factory, ontology, manager, "influences", component, component);
        OWLObjectProperty dependsOn = createObjectProperty(factory, ontology, manager, "dependsOn", component, component);

        // TT&C Properties
        OWLObjectProperty receivesCommandFrom = createObjectProperty(factory, ontology, manager, "receivesCommandFrom", component, component);
        OWLObjectProperty sendsTelemetryTo = createObjectProperty(factory, ontology, manager, "sendsTelemetryTo", component, component);
        OWLObjectProperty feedsCommandTo = createObjectProperty(factory, ontology, manager, "feedsCommandTo", component, component);
        OWLObjectProperty feedsTelemetryTo = createObjectProperty(factory, ontology, manager, "feedsTelemetryTo", component, component);

        // Power Subsystem Specific Properties
        OWLObjectProperty feedsPowerTo = createObjectProperty(factory, ontology, manager, "feedsPowerTo", component, component);
        OWLObjectProperty suppliesPowerTo = createObjectProperty(factory, ontology, manager, "suppliesPowerTo", component, component);
                OWLObjectProperty distributesPowerTo = createObjectProperty(factory, ontology, manager, "distributesPowerTo", component, component);
        OWLObjectProperty feedsSignalTo = createObjectProperty(factory, ontology, manager, "feedsSignalTo", component, component);
        OWLObjectProperty supports = createObjectProperty(factory, ontology, manager, "supports", component, component);
        OWLObjectProperty connectedTo = createObjectProperty(factory, ontology, manager, "connectedTo", component, component);

        // Thermal Properties
        OWLObjectProperty protects = createObjectProperty(factory, ontology, manager, "protects", component, component);
        OWLObjectProperty cools = createObjectProperty(factory, ontology, manager, "cools", component, component);
        OWLObjectProperty heats = createObjectProperty(factory, ontology, manager, "heats", component, component);
        OWLObjectProperty transfersHeatTo = createObjectProperty(factory, ontology, manager, "transfersHeatTo", component, component);

        // --- STRUCTURE PROPERTIES ---
        OWLObjectProperty supportsStructure = createObjectProperty(factory, ontology, manager, "supportsStructure", component, component);
        OWLObjectProperty physicallySupports = createObjectProperty(factory, ontology, manager, "physicallySupports", component, component);
        OWLObjectProperty connectedStructurally = createObjectProperty(factory, ontology, manager, "connectedStructurally", component, component);
        OWLObjectProperty mountedOn = createObjectProperty(factory, ontology, manager, "mountedOn", component, component);
        OWLObjectProperty isolatesVibration = createObjectProperty(factory, ontology, manager, "isolatesVibration", component, component);


        // --- DATA PROPERTIES ---
        // AOCS
        OWLDataProperty hasAngularRate = createDataProperty(factory, ontology, manager, "hasAngularRate", gyroscope);
        OWLDataProperty hasTrackingError = createDataProperty(factory, ontology, manager, "hasTrackingError", starTracker);
        OWLDataProperty hasSunVectorError = createDataProperty(factory, ontology, manager, "hasSunVectorError", sunSensor);
        OWLDataProperty hasMagneticFieldError = createDataProperty(factory, ontology, manager, "hasMagneticFieldError", magnetometer);
        OWLDataProperty hasEarthVectorError = createDataProperty(factory, ontology, manager, "hasEarthVectorError", earthSensor);
        OWLDataProperty hasPositionError = createDataProperty(factory, ontology, manager, "hasPositionError", gnssReceiver);
        OWLDataProperty hasVibration = createDataProperty(factory, ontology, manager, "hasVibration", reactionWheel);
        OWLDataProperty hasMagneticMoment = createDataProperty(factory, ontology, manager, "hasMagneticMoment", magnetorquer);
        OWLDataProperty hasThrust = createDataProperty(factory, ontology, manager, "hasThrust", thruster);
        OWLDataProperty hasAttitudeError = createDataProperty(factory, ontology, manager, "hasAttitudeError", attitudeControl);
        OWLDataProperty hasOrbitDeviation = createDataProperty(factory, ontology, manager, "hasOrbitDeviation", orbitControl);
        OWLDataProperty hasPointingError = createDataProperty(factory, ontology, manager, "hasPointingError", antennaPointing);
        OWLDataProperty hasSeverity = createDataProperty(factory, ontology, manager, "hasSeverity", fault, OWL2Datatype.XSD_STRING.getIRI());

        // Power
        OWLDataProperty hasVoltageOutput = createDataProperty(factory, ontology, manager, "hasVoltageOutput", solarCellClass);
        OWLDataProperty hasPanelEfficiency = createDataProperty(factory, ontology, manager, "hasPanelEfficiency", solarPanelClass);
        OWLDataProperty hasGeneratedPower = createDataProperty(factory, ontology, manager, "hasGeneratedPower", solarArrayClass);
        OWLDataProperty hasChargeLevel = createDataProperty(factory, ontology, manager, "hasChargeLevel", batteryClass);
        OWLDataProperty hasTemperature = createDataProperty(factory, ontology, manager, "hasTemperature", batteryClass);
        OWLDataProperty hasChargeRate = createDataProperty(factory, ontology, manager, "hasChargeRate", bccClass);
        OWLDataProperty hasOutputVoltage = createDataProperty(factory, ontology, manager, "hasOutputVoltage", pcduClass);
        OWLDataProperty hasDistributionLoad = createDataProperty(factory, ontology, manager, "hasDistributionLoad", pcduClass);
        OWLDataProperty hasBusVoltage = createDataProperty(factory, ontology, manager, "hasBusVoltage", busLineClass);
        OWLDataProperty hasRegulatedVoltage = createDataProperty(factory, ontology, manager, "hasRegulatedVoltage", voltRegClass);

        // Thermal Data Properties
        OWLDataProperty hasHeatLevel = createDataProperty(factory, ontology, manager, "hasHeatLevel", heaterClass);
        OWLDataProperty hasCoolingEfficiency = createDataProperty(factory, ontology, manager, "hasCoolingEfficiency", radiatorClass);
        OWLDataProperty hasSensorAccuracy = createDataProperty(factory, ontology, manager, "hasSensorAccuracy", tempSensorClass);

        // Comm & Thermal
        OWLDataProperty hasSignalStrength = createDataProperty(factory, ontology, manager, "hasSignalStrength", component);
        OWLDataProperty hasTempTele = createDataProperty(factory, ontology, manager, "hasTemperatureTele", thermalSensor);

        // Enhanced Solar Model
        OWLDataProperty hasDegradationRate = createDataProperty(factory, ontology, manager, "hasDegradationRate", solarPanelClass);
        OWLDataProperty hasIrradiance = createDataProperty(factory, ontology, manager, "hasIrradiance", solarArrayClass);

        // Enhanced Battery Model
        OWLDataProperty hasDischargeRate = createDataProperty(factory, ontology, manager, "hasDischargeRate", batteryClass);
        OWLDataProperty hasCycleCount = createDataProperty(factory, ontology, manager, "hasCycleCount", batteryClass);

        // Enhanced PCDU Model
        OWLDataProperty hasLoadDistributionBalance = createDataProperty(factory, ontology, manager, "hasLoadDistributionBalance", pcduClass);

        // System Priority
                OWLDataProperty hasPriority = createDataProperty(factory, ontology, manager, "hasPriority", subsystem, OWL2Datatype.XSD_STRING.getIRI());
        OWLDataProperty hasGain = createDataProperty(factory, ontology, manager, "hasGain", lnaClass);
        OWLDataProperty hasBandwidth = createDataProperty(factory, ontology, manager, "hasBandwidth", filterClass);
        OWLDataProperty hasFrequencyShift = createDataProperty(factory, ontology, manager, "hasFrequencyShift", downconverterClass);
        OWLDataProperty hasBitErrorRate = createDataProperty(factory, ontology, manager, "hasBitErrorRate", demodulatorClass);
        OWLDataProperty hasEncodingRate = createDataProperty(factory, ontology, manager, "hasEncodingRate", encoderClass);
        OWLDataProperty hasCarrierFrequency = createDataProperty(factory, ontology, manager, "hasCarrierFrequency", upconverterClass);
        OWLDataProperty hasOutputPower = createDataProperty(factory, ontology, manager, "hasOutputPower", powerAmpClass);
        OWLDataProperty hasFrequencyStability = createDataProperty(factory, ontology, manager, "hasFrequencyStability", loClass);
        OWLDataProperty hasIsolation = createDataProperty(factory, ontology, manager, "hasIsolation", duplexerClass);

        OWLDataProperty hasLinkQuality = createDataProperty(factory, ontology, manager, "hasLinkQuality", groundStationClass);
        OWLDataProperty hasDecodingError = createDataProperty(factory, ontology, manager, "hasDecodingError", decoderClass);
        OWLDataProperty hasTrackingAccuracy = createDataProperty(factory, ontology, manager, "hasTrackingAccuracy", trackingUnitClass);
        OWLDataProperty hasEncodingQuality = createDataProperty(factory, ontology, manager, "hasEncodingQuality", telemetryEncoderClass);
        OWLDataProperty hasTransmitPower = createDataProperty(factory, ontology, manager, "hasTransmitPower", ttcRadioClass);

        // --- INDIVIDUALS: FAULTS ---
        // AOCS Faults
        OWLNamedIndividual fGyro = createNamedIndividual(factory, ontology, manager, "GyroFault_001");
        OWLNamedIndividual fGnss = createNamedIndividual(factory, ontology, manager, "GNSSFault_001");
        OWLNamedIndividual fStar = createNamedIndividual(factory, ontology, manager, "StarTrackerFault_001");
        OWLNamedIndividual fSun = createNamedIndividual(factory, ontology, manager, "SunSensorFault_001");
        OWLNamedIndividual fMag = createNamedIndividual(factory, ontology, manager, "MagnetometerFault_001");
        OWLNamedIndividual fEarth = createNamedIndividual(factory, ontology, manager, "EarthSensorFault_001");
        OWLNamedIndividual fRw = createNamedIndividual(factory, ontology, manager, "ReactionWheelFault_001");
        OWLNamedIndividual fMtq = createNamedIndividual(factory, ontology, manager, "MagnetorquerFault_001");
        OWLNamedIndividual fThr = createNamedIndividual(factory, ontology, manager, "ThrusterFault_001");
        OWLNamedIndividual fAc = createNamedIndividual(factory, ontology, manager, "AttitudeControlFault_001");
        OWLNamedIndividual fOrb = createNamedIndividual(factory, ontology, manager, "OrbitControlFault_001");
        OWLNamedIndividual fAnt = createNamedIndividual(factory, ontology, manager, "AntennaPointingFault_001");
        OWLNamedIndividual fSig = createNamedIndividual(factory, ontology, manager, "SignalLossFault_001");
        OWLNamedIndividual fHeat = createNamedIndividual(factory, ontology, manager, "OverheatFault_001");

        addClassAssertion(factory, ontology, manager, gyroFault, fGyro);
        addClassAssertion(factory, ontology, manager, gnssFault, fGnss);
        addClassAssertion(factory, ontology, manager, starTrackerFault, fStar);
        addClassAssertion(factory, ontology, manager, sunSensorFault, fSun);
        addClassAssertion(factory, ontology, manager, magnetometerFault, fMag);
        addClassAssertion(factory, ontology, manager, earthSensorFault, fEarth);
        addClassAssertion(factory, ontology, manager, reactionWheelFault, fRw);
        addClassAssertion(factory, ontology, manager, magnetorquerFault, fMtq);
        addClassAssertion(factory, ontology, manager, thrusterFault, fThr);
        addClassAssertion(factory, ontology, manager, attitudeControlFault, fAc);
        addClassAssertion(factory, ontology, manager, orbitControlFault, fOrb);
        addClassAssertion(factory, ontology, manager, antennaPointingFault, fAnt);
        addClassAssertion(factory, ontology, manager, signalLossFault, fSig);
        addClassAssertion(factory, ontology, manager, overheatFault, fHeat);

        // --- RECOVERY ACTIONS ---
        OWLNamedIndividual safeMode = createNamedIndividual(factory, ontology, manager, "SafeMode_01");
        OWLNamedIndividual heaterOn = createNamedIndividual(factory, ontology, manager, "HeaterON_01");
        OWLNamedIndividual warningAction = createNamedIndividual(factory, ontology, manager, "Warning_01");
        addClassAssertion(factory, ontology, manager, action, safeMode);
        addClassAssertion(factory, ontology, manager, action, heaterOn);
        addClassAssertion(factory, ontology, manager, action, warningAction);

        // Power Faults
        OWLNamedIndividual fSolar = createNamedIndividual(factory, ontology, manager, "SolarFault_001");
        OWLNamedIndividual fBat = createNamedIndividual(factory, ontology, manager, "BatteryFault_001");
        OWLNamedIndividual fBcc = createNamedIndividual(factory, ontology, manager, "ChargeControllerFault_001");
        OWLNamedIndividual fPcdu = createNamedIndividual(factory, ontology, manager, "PCDUFault_001");
        OWLNamedIndividual fBus = createNamedIndividual(factory, ontology, manager, "BusFault_001");
        OWLNamedIndividual fReg = createNamedIndividual(factory, ontology, manager, "RegulatorFault_001");

        addClassAssertion(factory, ontology, manager, solarFault, fSolar);
        addClassAssertion(factory, ontology, manager, batteryFaultClass, fBat);
        addClassAssertion(factory, ontology, manager, bccFault, fBcc);
        addClassAssertion(factory, ontology, manager, pcduFault, fPcdu);
        addClassAssertion(factory, ontology, manager, busFault, fBus);
                addClassAssertion(factory, ontology, manager, regFault, fReg);

        // --- COMMUNICATION FAULT INDIVIDUALS ---
        OWLNamedIndividual fLna = createNamedIndividual(factory, ontology, manager, "LNAFault_001");
        OWLNamedIndividual fFilter = createNamedIndividual(factory, ontology, manager, "FilterFault_001");
        OWLNamedIndividual fDc = createNamedIndividual(factory, ontology, manager, "DownconverterFault_001");
        OWLNamedIndividual fDemo = createNamedIndividual(factory, ontology, manager, "DemodulatorFault_001");
        OWLNamedIndividual fEnc = createNamedIndividual(factory, ontology, manager, "EncoderFault_001");
        OWLNamedIndividual fUc = createNamedIndividual(factory, ontology, manager, "UpconverterFault_001");
        OWLNamedIndividual fAmp = createNamedIndividual(factory, ontology, manager, "AmplifierFault_001");
        OWLNamedIndividual fLo = createNamedIndividual(factory, ontology, manager, "LOFault_001");
        OWLNamedIndividual fDupl = createNamedIndividual(factory, ontology, manager, "DuplexerFault_001");
        OWLNamedIndividual fAntComm = createNamedIndividual(factory, ontology, manager, "AntennaFault_001");

        addClassAssertion(factory, ontology, manager, lnaFault, fLna);
        addClassAssertion(factory, ontology, manager, filterFault, fFilter);
        addClassAssertion(factory, ontology, manager, downconverterFault, fDc);
        addClassAssertion(factory, ontology, manager, demodulatorFault, fDemo);
        addClassAssertion(factory, ontology, manager, encoderFault, fEnc);
        addClassAssertion(factory, ontology, manager, upconverterFault, fUc);
        addClassAssertion(factory, ontology, manager, amplifierFault, fAmp);
        addClassAssertion(factory, ontology, manager, loFault, fLo);
        addClassAssertion(factory, ontology, manager, duplexerFault, fDupl);
        addClassAssertion(factory, ontology, manager, antennaFault, fAntComm);

        // Thermal Fault Individuals
        OWLNamedIndividual fOverheat = createNamedIndividual(factory, ontology, manager, "OverheatFault_001");
        OWLNamedIndividual fUnderheat = createNamedIndividual(factory, ontology, manager, "UnderheatFault_001");
        OWLNamedIndividual fSensor = createNamedIndividual(factory, ontology, manager, "SensorFault_001");
        OWLNamedIndividual fHeater = createNamedIndividual(factory, ontology, manager, "HeaterFault_001");
        addClassAssertion(factory, ontology, manager, overheatFault, fOverheat);
        addClassAssertion(factory, ontology, manager, underheatFault, fUnderheat);
        addClassAssertion(factory, ontology, manager, sensorFault, fSensor);
        addClassAssertion(factory, ontology, manager, heaterFaultClass, fHeater);

        // --- TT&C FAULT CLASSES ---
        OWLClass commandReceiverFault = createClass(factory, ontology, manager, "CommandReceiverFault");
        OWLClass decoderFault = createClass(factory, ontology, manager, "DecoderFault");
        OWLClass trackingFault = createClass(factory, ontology, manager, "TrackingFault");
        OWLClass telemetryEncoderFault = createClass(factory, ontology, manager, "TelemetryEncoderFault");
        OWLClass ttcRadioFault = createClass(factory, ontology, manager, "TTCRadioFault");
        OWLClass ttcAntennaFault = createClass(factory, ontology, manager, "TTCAntennaFault");
        OWLClass groundLinkFault = createClass(factory, ontology, manager, "GroundLinkFault");

        addSubClassAxiom(factory, ontology, manager, commandReceiverFault, fault);
        addSubClassAxiom(factory, ontology, manager, decoderFault, fault);
        addSubClassAxiom(factory, ontology, manager, trackingFault, fault);
        addSubClassAxiom(factory, ontology, manager, telemetryEncoderFault, fault);
        addSubClassAxiom(factory, ontology, manager, ttcRadioFault, fault);
        addSubClassAxiom(factory, ontology, manager, ttcAntennaFault, fault);
        addSubClassAxiom(factory, ontology, manager, groundLinkFault, fault);

        // --- TT&C FAULT INDIVIDUALS ---
        OWLNamedIndividual fCr = createNamedIndividual(factory, ontology, manager, "CommandReceiverFault_001");
        OWLNamedIndividual fDec = createNamedIndividual(factory, ontology, manager, "DecoderFault_001");
        OWLNamedIndividual fTrk = createNamedIndividual(factory, ontology, manager, "TrackingFault_001");
        OWLNamedIndividual fEncTtc = createNamedIndividual(factory, ontology, manager, "TelemetryEncoderFault_001");
        OWLNamedIndividual fRad = createNamedIndividual(factory, ontology, manager, "TTCRadioFault_001");
        OWLNamedIndividual fAntTtc = createNamedIndividual(factory, ontology, manager, "TTCAntennaFault_001");
        OWLNamedIndividual fGnd = createNamedIndividual(factory, ontology, manager, "GroundLinkFault_001");

        addClassAssertion(factory, ontology, manager, commandReceiverFault, fCr);
        addClassAssertion(factory, ontology, manager, decoderFault, fDec);
        addClassAssertion(factory, ontology, manager, trackingFault, fTrk);
        addClassAssertion(factory, ontology, manager, telemetryEncoderFault, fEncTtc);
        addClassAssertion(factory, ontology, manager, ttcRadioFault, fRad);
        addClassAssertion(factory, ontology, manager, ttcAntennaFault, fAntTtc);
        addClassAssertion(factory, ontology, manager, groundLinkFault, fGnd);
        // Severities
        addDataPropertyAssertion(factory, ontology, manager, hasSeverity, fSolar, "MEDIUM");
        addDataPropertyAssertion(factory, ontology, manager, hasSeverity, fBat, "HIGH");
        addDataPropertyAssertion(factory, ontology, manager, hasSeverity, fBcc, "HIGH");
        addDataPropertyAssertion(factory, ontology, manager, hasSeverity, fPcdu, "HIGH");
        addDataPropertyAssertion(factory, ontology, manager, hasSeverity, fBus, "HIGH");
                addDataPropertyAssertion(factory, ontology, manager, hasSeverity, fReg, "HIGH");

        addDataPropertyAssertion(factory, ontology, manager, hasSeverity, fLna, "HIGH");
        addDataPropertyAssertion(factory, ontology, manager, hasSeverity, fDemo, "HIGH");
        addDataPropertyAssertion(factory, ontology, manager, hasSeverity, fAmp, "HIGH");
        addDataPropertyAssertion(factory, ontology, manager, hasSeverity, fAntComm, "HIGH");
        addDataPropertyAssertion(factory, ontology, manager, hasSeverity, fFilter, "MEDIUM");
        addDataPropertyAssertion(factory, ontology, manager, hasSeverity, fDc, "MEDIUM");
        addDataPropertyAssertion(factory, ontology, manager, hasSeverity, fEnc, "MEDIUM");
        addDataPropertyAssertion(factory, ontology, manager, hasSeverity, fUc, "MEDIUM");
        addDataPropertyAssertion(factory, ontology, manager, hasSeverity, fLo, "MEDIUM");
        addDataPropertyAssertion(factory, ontology, manager, hasSeverity, fDupl, "MEDIUM");

        // TT&C Severities
        addDataPropertyAssertion(factory, ontology, manager, hasSeverity, fGnd, "HIGH");
        addDataPropertyAssertion(factory, ontology, manager, hasSeverity, fRad, "HIGH");
        addDataPropertyAssertion(factory, ontology, manager, hasSeverity, fCr, "HIGH");
        addDataPropertyAssertion(factory, ontology, manager, hasSeverity, fDec, "MEDIUM");
        addDataPropertyAssertion(factory, ontology, manager, hasSeverity, fTrk, "MEDIUM");
        addDataPropertyAssertion(factory, ontology, manager, hasSeverity, fEncTtc, "MEDIUM");
        addDataPropertyAssertion(factory, ontology, manager, hasSeverity, fAntTtc, "MEDIUM");

        // AOCS Severities (Setting some to HIGH for triggers)
        addDataPropertyAssertion(factory, ontology, manager, hasSeverity, fGyro, "HIGH");
        addDataPropertyAssertion(factory, ontology, manager, hasSeverity, fGnss, "HIGH");

        // --- INDIVIDUALS: SUBSYSTEMS ---
        OWLNamedIndividual subAocs = createNamedIndividual(factory, ontology, manager, "AOCS_01");
        OWLNamedIndividual subPower = createNamedIndividual(factory, ontology, manager, "Power_01");
        OWLNamedIndividual subComm = createNamedIndividual(factory, ontology, manager, "Comm_01");
        OWLNamedIndividual subThermal = createNamedIndividual(factory, ontology, manager, "Thermal_01");
        addClassAssertion(factory, ontology, manager, aocs, subAocs);
        addClassAssertion(factory, ontology, manager, powerSystem, subPower);
        addClassAssertion(factory, ontology, manager, communicationSystem, subComm);
                addClassAssertion(factory, ontology, manager, thermalSystem, subThermal);

        // --- STRUCTURE INDIVIDUALS ---
        OWLNamedIndividual subStructure = createNamedIndividual(factory, ontology, manager, "Structure_01");
        addClassAssertion(factory, ontology, manager, structureSubsystem, subStructure);

        OWLNamedIndividual frame01 = createNamedIndividual(factory, ontology, manager, "Frame_01");
        OWLNamedIndividual panel01 = createNamedIndividual(factory, ontology, manager, "Panel_01");
        OWLNamedIndividual bracket01 = createNamedIndividual(factory, ontology, manager, "Bracket_01");
        OWLNamedIndividual mount01 = createNamedIndividual(factory, ontology, manager, "Mount_01");
        OWLNamedIndividual hinge01 = createNamedIndividual(factory, ontology, manager, "Hinge_01");

        OWLNamedIndividual deployment01 = createNamedIndividual(factory, ontology, manager, "Deployment_01");
        OWLNamedIndividual isolation01 = createNamedIndividual(factory, ontology, manager, "Isolation_01");
        OWLNamedIndividual antMount01 = createNamedIndividual(factory, ontology, manager, "AntennaMount_01");
        OWLNamedIndividual solarMount01 = createNamedIndividual(factory, ontology, manager, "SolarMount_01");

        OWLNamedIndividual switch01 = createNamedIndividual(factory, ontology, manager, "Switch_01");
        OWLNamedIndividual vibSensor01 = createNamedIndividual(factory, ontology, manager, "VibrationSensor_01");
        OWLNamedIndividual posSensor01 = createNamedIndividual(factory, ontology, manager, "PositionSensor_01");

        addClassAssertion(factory, ontology, manager, satelliteFrame, frame01);
        addClassAssertion(factory, ontology, manager, panel, panel01);
        addClassAssertion(factory, ontology, manager, bracket, bracket01);
        addClassAssertion(factory, ontology, manager, mount, mount01);
        addClassAssertion(factory, ontology, manager, hinge, hinge01);

        addClassAssertion(factory, ontology, manager, deploymentMechanism, deployment01);
        addClassAssertion(factory, ontology, manager, vibrationIsolation, isolation01);
        addClassAssertion(factory, ontology, manager, antennaMountClass, antMount01);
        addClassAssertion(factory, ontology, manager, solarPanelMountClass, solarMount01);

        addClassAssertion(factory, ontology, manager, deploymentSwitch, switch01);
        addClassAssertion(factory, ontology, manager, vibrationSensorClass, vibSensor01);
        addClassAssertion(factory, ontology, manager, positionFeedback, posSensor01);

        // --- STRUCTURE FAULT INDIVIDUALS ---
        OWLNamedIndividual fStruct = createNamedIndividual(factory, ontology, manager, "StructuralFailure_001");
        OWLNamedIndividual fDepl = createNamedIndividual(factory, ontology, manager, "DeploymentFailure_001");
        OWLNamedIndividual fVib = createNamedIndividual(factory, ontology, manager, "VibrationFailure_001");
        OWLNamedIndividual fMount = createNamedIndividual(factory, ontology, manager, "MountFailure_001");
        OWLNamedIndividual fAlign = createNamedIndividual(factory, ontology, manager, "AlignmentFailure_001");

        addClassAssertion(factory, ontology, manager, structuralFailure, fStruct);
        addClassAssertion(factory, ontology, manager, deploymentFailure, fDepl);
        addClassAssertion(factory, ontology, manager, vibrationFailure, fVib);
        addClassAssertion(factory, ontology, manager, mountFailureClass, fMount);
        addClassAssertion(factory, ontology, manager, alignmentFailure, fAlign);

        addDataPropertyAssertion(factory, ontology, manager, hasSeverity, fStruct, "HIGH");
        addDataPropertyAssertion(factory, ontology, manager, hasSeverity, fDepl, "MEDIUM");
        addDataPropertyAssertion(factory, ontology, manager, hasSeverity, fVib, "MEDIUM");

        // --- COMMUNICATION INDIVIDUALS ---
        OWLNamedIndividual lna01 = createNamedIndividual(factory, ontology, manager, "LNA_01");
        OWLNamedIndividual filter01 = createNamedIndividual(factory, ontology, manager, "Filter_01");
        OWLNamedIndividual dc01 = createNamedIndividual(factory, ontology, manager, "Downconverter_01");
        OWLNamedIndividual demo01 = createNamedIndividual(factory, ontology, manager, "Demodulator_01");
        OWLNamedIndividual enc01 = createNamedIndividual(factory, ontology, manager, "Encoder_01");
        OWLNamedIndividual uc01 = createNamedIndividual(factory, ontology, manager, "Upconverter_01");
        OWLNamedIndividual amp01 = createNamedIndividual(factory, ontology, manager, "PowerAmp_01");
        OWLNamedIndividual lo01 = createNamedIndividual(factory, ontology, manager, "LO_01");
        OWLNamedIndividual dupl01 = createNamedIndividual(factory, ontology, manager, "Duplexer_01");
        OWLNamedIndividual ant01_comm = createNamedIndividual(factory, ontology, manager, "Antenna_01");

        addClassAssertion(factory, ontology, manager, lnaClass, lna01);
        addClassAssertion(factory, ontology, manager, filterClass, filter01);
        addClassAssertion(factory, ontology, manager, downconverterClass, dc01);
        addClassAssertion(factory, ontology, manager, demodulatorClass, demo01);
        addClassAssertion(factory, ontology, manager, encoderClass, enc01);
        addClassAssertion(factory, ontology, manager, upconverterClass, uc01);
        addClassAssertion(factory, ontology, manager, powerAmpClass, amp01);
        addClassAssertion(factory, ontology, manager, loClass, lo01);
        addClassAssertion(factory, ontology, manager, duplexerClass, dupl01);
        addClassAssertion(factory, ontology, manager, antennaClass, ant01_comm);

        List<OWLNamedIndividual> commComps = Arrays.asList(lna01, filter01, dc01, demo01, enc01, uc01, amp01, lo01, dupl01, ant01_comm);
        for (OWLNamedIndividual c : commComps) addObjectPropertyAssertion(factory, ontology, manager, belongsToSubsystem, c, subComm);

        // --- TT&C INDIVIDUALS ---
        OWLNamedIndividual subTtc = createNamedIndividual(factory, ontology, manager, "TTC_01");
        addClassAssertion(factory, ontology, manager, communicationSystem, subTtc);

        OWLNamedIndividual cr01 = createNamedIndividual(factory, ontology, manager, "CommandReceiver_01");
        OWLNamedIndividual dec01 = createNamedIndividual(factory, ontology, manager, "Decoder_01");
        OWLNamedIndividual trk01 = createNamedIndividual(factory, ontology, manager, "TrackingUnit_01");
        OWLNamedIndividual encTtc01 = createNamedIndividual(factory, ontology, manager, "TelemetryEncoder_01");
        OWLNamedIndividual rad01 = createNamedIndividual(factory, ontology, manager, "TTCRadio_01");
        OWLNamedIndividual antTtc01 = createNamedIndividual(factory, ontology, manager, "TTCAntenna_01");
        OWLNamedIndividual gs01 = createNamedIndividual(factory, ontology, manager, "GroundStation_01");

        addClassAssertion(factory, ontology, manager, commandReceiverClass, cr01);
        addClassAssertion(factory, ontology, manager, decoderClass, dec01);
        addClassAssertion(factory, ontology, manager, trackingUnitClass, trk01);
        addClassAssertion(factory, ontology, manager, telemetryEncoderClass, encTtc01);
        addClassAssertion(factory, ontology, manager, ttcRadioClass, rad01);
        addClassAssertion(factory, ontology, manager, ttcAntennaClass, antTtc01);
        addClassAssertion(factory, ontology, manager, groundStationClass, gs01);

        List<OWLNamedIndividual> ttcComps = Arrays.asList(cr01, dec01, trk01, encTtc01, rad01, antTtc01);
        for (OWLNamedIndividual c : ttcComps) addObjectPropertyAssertion(factory, ontology, manager, belongsToSubsystem, c, subTtc);

        // --- THERMAL INDIVIDUALS ---
        OWLNamedIndividual mli01 = createNamedIndividual(factory, ontology, manager, "MLI_01");
        OWLNamedIndividual coating01 = createNamedIndividual(factory, ontology, manager, "Coating_01");
        OWLNamedIndividual radiator01 = createNamedIndividual(factory, ontology, manager, "Radiator_01");
        OWLNamedIndividual path01 = createNamedIndividual(factory, ontology, manager, "ThermalPath_01");
        OWLNamedIndividual sensor01 = createNamedIndividual(factory, ontology, manager, "TempSensor_01");
        OWLNamedIndividual heater01 = createNamedIndividual(factory, ontology, manager, "Heater_01");
        OWLNamedIndividual thermo01 = createNamedIndividual(factory, ontology, manager, "Thermostat_01");

        OWLNamedIndividual batTemp01 = createNamedIndividual(factory, ontology, manager, "BatteryTemp_01");
        OWLNamedIndividual obcTemp01 = createNamedIndividual(factory, ontology, manager, "OBCTemp_01");
        OWLNamedIndividual rfTemp01 = createNamedIndividual(factory, ontology, manager, "RFTemp_01");
        OWLNamedIndividual solarTemp01 = createNamedIndividual(factory, ontology, manager, "SolarTemp_01");

        addClassAssertion(factory, ontology, manager, mliLayerClass, mli01);
        addClassAssertion(factory, ontology, manager, coatingClass, coating01);
        addClassAssertion(factory, ontology, manager, radiatorClass, radiator01);
        addClassAssertion(factory, ontology, manager, thermalPathClass, path01);
        addClassAssertion(factory, ontology, manager, tempSensorClass, sensor01);
        addClassAssertion(factory, ontology, manager, heaterClass, heater01);
        addClassAssertion(factory, ontology, manager, thermostatClass, thermo01);

        addClassAssertion(factory, ontology, manager, batteryTempClass, batTemp01);
        addClassAssertion(factory, ontology, manager, obcTempClass, obcTemp01);
        addClassAssertion(factory, ontology, manager, rfTempClass, rfTemp01);
        addClassAssertion(factory, ontology, manager, solarPanelTempClass, solarTemp01);

        List<OWLNamedIndividual> thermalComps = Arrays.asList(mli01, coating01, radiator01, path01, sensor01, heater01, thermo01, batTemp01, obcTemp01, rfTemp01, solarTemp01);
        for (OWLNamedIndividual c : thermalComps) addObjectPropertyAssertion(factory, ontology, manager, belongsToSubsystem, c, subThermal);


        // --- INDIVIDUALS: COMPONENTS (AOCS) ---
        OWLNamedIndividual gyro01 = createNamedIndividual(factory, ontology, manager, "GYRO_01");
        OWLNamedIndividual str01 = createNamedIndividual(factory, ontology, manager, "STR_01");
        OWLNamedIndividual sun01 = createNamedIndividual(factory, ontology, manager, "SUN_01");
        OWLNamedIndividual mag01 = createNamedIndividual(factory, ontology, manager, "MAG_01");
        OWLNamedIndividual earth01 = createNamedIndividual(factory, ontology, manager, "EARTH_01");
        OWLNamedIndividual gnss01 = createNamedIndividual(factory, ontology, manager, "GNSS_01");
        OWLNamedIndividual rw01 = createNamedIndividual(factory, ontology, manager, "RW_01");
        OWLNamedIndividual mtq01 = createNamedIndividual(factory, ontology, manager, "MTQ_01");
        OWLNamedIndividual thr01 = createNamedIndividual(factory, ontology, manager, "THR_01");
        OWLNamedIndividual ac01 = createNamedIndividual(factory, ontology, manager, "AC_01");
        OWLNamedIndividual orb01 = createNamedIndividual(factory, ontology, manager, "ORB_01");
        OWLNamedIndividual ant01 = createNamedIndividual(factory, ontology, manager, "ANT_01");
        
        addClassAssertion(factory, ontology, manager, gyroscope, gyro01);
        addClassAssertion(factory, ontology, manager, starTracker, str01);
        addClassAssertion(factory, ontology, manager, sunSensor, sun01);
        addClassAssertion(factory, ontology, manager, magnetometer, mag01);
        addClassAssertion(factory, ontology, manager, earthSensor, earth01);
        addClassAssertion(factory, ontology, manager, gnssReceiver, gnss01);
        addClassAssertion(factory, ontology, manager, reactionWheel, rw01);
        addClassAssertion(factory, ontology, manager, magnetorquer, mtq01);
        addClassAssertion(factory, ontology, manager, thruster, thr01);
        addClassAssertion(factory, ontology, manager, attitudeControl, ac01);
        addClassAssertion(factory, ontology, manager, orbitControl, orb01);
        addClassAssertion(factory, ontology, manager, antennaPointing, ant01);

        List<OWLNamedIndividual> aocsList = Arrays.asList(gyro01, str01, sun01, mag01, earth01, gnss01, rw01, mtq01, thr01, ac01, orb01, ant01);
        for (OWLNamedIndividual c : aocsList) addObjectPropertyAssertion(factory, ontology, manager, belongsToSubsystem, c, subAocs);

        // --- INDIVIDUALS: COMPONENTS (POWER) ---
        OWLNamedIndividual solarCell01 = createNamedIndividual(factory, ontology, manager, "SolarCell_01");
        OWLNamedIndividual solarPanel01 = createNamedIndividual(factory, ontology, manager, "SolarPanel_01");
        OWLNamedIndividual solarArray01 = createNamedIndividual(factory, ontology, manager, "SolarArray_01");
        OWLNamedIndividual bat01 = createNamedIndividual(factory, ontology, manager, "Battery_01");
        OWLNamedIndividual bcc01 = createNamedIndividual(factory, ontology, manager, "BCC_01");
        OWLNamedIndividual pcdu01 = createNamedIndividual(factory, ontology, manager, "PCDU_01");
        OWLNamedIndividual busLine01 = createNamedIndividual(factory, ontology, manager, "BusLine_01");
        OWLNamedIndividual voltReg01 = createNamedIndividual(factory, ontology, manager, "VoltageReg_01");

        addClassAssertion(factory, ontology, manager, solarCellClass, solarCell01);
        addClassAssertion(factory, ontology, manager, solarPanelClass, solarPanel01);
        addClassAssertion(factory, ontology, manager, solarArrayClass, solarArray01);
        addClassAssertion(factory, ontology, manager, batteryClass, bat01);
        addClassAssertion(factory, ontology, manager, bccClass, bcc01);
        addClassAssertion(factory, ontology, manager, pcduClass, pcdu01);
        addClassAssertion(factory, ontology, manager, busLineClass, busLine01);
        addClassAssertion(factory, ontology, manager, voltRegClass, voltReg01);

        List<OWLNamedIndividual> powerList = Arrays.asList(solarCell01, solarPanel01, solarArray01, bat01, bcc01, pcdu01, busLine01, voltReg01);
        for (OWLNamedIndividual c : powerList) addObjectPropertyAssertion(factory, ontology, manager, belongsToSubsystem, c, subPower);

        // --- TELEMETRY VALUES ---
        // AOCS (Trigger rules)
        addDataPropertyAssertion(factory, ontology, manager, hasAngularRate, gyro01, 15.0);
        addDataPropertyAssertion(factory, ontology, manager, hasPositionError, gnss01, 50.0);
        addDataPropertyAssertion(factory, ontology, manager, hasTrackingError, str01, 2.0);
        addDataPropertyAssertion(factory, ontology, manager, hasSunVectorError, sun01, 1.5);
        addDataPropertyAssertion(factory, ontology, manager, hasMagneticFieldError, mag01, 0.0);
        addDataPropertyAssertion(factory, ontology, manager, hasEarthVectorError, earth01, 2.0);
        addDataPropertyAssertion(factory, ontology, manager, hasVibration, rw01, 7.0);
        addDataPropertyAssertion(factory, ontology, manager, hasMagneticMoment, mtq01, 0.0);
        addDataPropertyAssertion(factory, ontology, manager, hasThrust, thr01, 0.0);
        addDataPropertyAssertion(factory, ontology, manager, hasAttitudeError, ac01, 12.0);
        addDataPropertyAssertion(factory, ontology, manager, hasOrbitDeviation, orb01, 3.0);
        addDataPropertyAssertion(factory, ontology, manager, hasPointingError, ant01, 5.0);

        // Power (Trigger rules)
        addDataPropertyAssertion(factory, ontology, manager, hasVoltageOutput, solarCell01, 0.2);
        addDataPropertyAssertion(factory, ontology, manager, hasPanelEfficiency, solarPanel01, 40.0);
        addDataPropertyAssertion(factory, ontology, manager, hasGeneratedPower, solarArray01, 50.0);
        addDataPropertyAssertion(factory, ontology, manager, hasChargeLevel, bat01, 15.0);
        addDataPropertyAssertion(factory, ontology, manager, hasTemperature, bat01, 55.0);
        addDataPropertyAssertion(factory, ontology, manager, hasChargeRate, bcc01, 0.0);
        addDataPropertyAssertion(factory, ontology, manager, hasOutputVoltage, pcdu01, 18.0);
        addDataPropertyAssertion(factory, ontology, manager, hasDistributionLoad, pcdu01, 95.0);
        addDataPropertyAssertion(factory, ontology, manager, hasBusVoltage, busLine01, 10.0);
        addDataPropertyAssertion(factory, ontology, manager, hasRegulatedVoltage, voltReg01, 3.0);

        // Enhanced Solar Telemetry
        addDataPropertyAssertion(factory, ontology, manager, hasDegradationRate, solarPanel01, 0.85);
        addDataPropertyAssertion(factory, ontology, manager, hasIrradiance, solarArray01, 1100.0);

        // Enhanced Battery Telemetry
        addDataPropertyAssertion(factory, ontology, manager, hasDischargeRate, bat01, 25.0);
        addDataPropertyAssertion(factory, ontology, manager, hasCycleCount, bat01, 2850.0);

        // Enhanced PCDU Telemetry
        addDataPropertyAssertion(factory, ontology, manager, hasLoadDistributionBalance, pcdu01, 0.92);

        // System Priority
        addDataPropertyAssertion(factory, ontology, manager, hasPriority, subPower, "HIGH");
        addDataPropertyAssertion(factory, ontology, manager, hasPriority, subAocs, "HIGH");
                addDataPropertyAssertion(factory, ontology, manager, hasPriority, subComm, "MEDIUM");

        // --- COMMUNICATION TELEMETRY ---
        addDataPropertyAssertion(factory, ontology, manager, hasGain, lna01, 5.0);
        addDataPropertyAssertion(factory, ontology, manager, hasBandwidth, filter01, 1.0);
        addDataPropertyAssertion(factory, ontology, manager, hasFrequencyShift, dc01, 0.0);
        addDataPropertyAssertion(factory, ontology, manager, hasBitErrorRate, demo01, 0.2);
        addDataPropertyAssertion(factory, ontology, manager, hasEncodingRate, enc01, 0.0);
        addDataPropertyAssertion(factory, ontology, manager, hasCarrierFrequency, uc01, 0.0);
        addDataPropertyAssertion(factory, ontology, manager, hasOutputPower, amp01, 2.0);
        addDataPropertyAssertion(factory, ontology, manager, hasFrequencyStability, lo01, 0.1);
        addDataPropertyAssertion(factory, ontology, manager, hasIsolation, dupl01, 5.0);
        addDataPropertyAssertion(factory, ontology, manager, hasSignalStrength, ant01_comm, 10.0);

        // TT&C Telemetry
        addDataPropertyAssertion(factory, ontology, manager, hasSignalStrength, cr01, 10.0);
        addDataPropertyAssertion(factory, ontology, manager, hasDecodingError, dec01, 0.2);
        addDataPropertyAssertion(factory, ontology, manager, hasTrackingAccuracy, trk01, 0.5);
        addDataPropertyAssertion(factory, ontology, manager, hasEncodingQuality, encTtc01, 0.0);
        addDataPropertyAssertion(factory, ontology, manager, hasTransmitPower, rad01, 2.0);
        addDataPropertyAssertion(factory, ontology, manager, hasSignalStrength, antTtc01, 15.0);
        addDataPropertyAssertion(factory, ontology, manager, hasLinkQuality, gs01, 0.3);

        // Thermal Telemetry (Triggering Faults)
        addDataPropertyAssertion(factory, ontology, manager, hasTemperature, sensor01, 120.0); // Overheat trigger
        addDataPropertyAssertion(factory, ontology, manager, hasTemperature, batTemp01, 90.0);
        addDataPropertyAssertion(factory, ontology, manager, hasTemperature, obcTemp01, 95.0);
        addDataPropertyAssertion(factory, ontology, manager, hasTemperature, rfTemp01, 100.0);
        addDataPropertyAssertion(factory, ontology, manager, hasTemperature, solarTemp01, 110.0);
        addDataPropertyAssertion(factory, ontology, manager, hasHeatLevel, heater01, 0.0); // Heater fault trigger
        addDataPropertyAssertion(factory, ontology, manager, hasCoolingEfficiency, radiator01, 0.3);
        addDataPropertyAssertion(factory, ontology, manager, hasSensorAccuracy, sensor01, 0.4); // Sensor fault trigger


        // --- DEPENDENCY LINKS ---
        // AOCS
        addObjectPropertyAssertion(factory, ontology, manager, feedsDataTo, gyro01, ac01);
        addObjectPropertyAssertion(factory, ontology, manager, feedsDataTo, str01, ac01);
        addObjectPropertyAssertion(factory, ontology, manager, feedsDataTo, gnss01, orb01);
        addObjectPropertyAssertion(factory, ontology, manager, sendsCommandTo, ac01, rw01);
        addObjectPropertyAssertion(factory, ontology, manager, dependsOn, ac01, gyro01);
        addObjectPropertyAssertion(factory, ontology, manager, influences, gyro01, ac01);

        // Power
        addObjectPropertyAssertion(factory, ontology, manager, feedsPowerTo, solarCell01, solarPanel01);
        addObjectPropertyAssertion(factory, ontology, manager, feedsPowerTo, solarPanel01, solarArray01);
        addObjectPropertyAssertion(factory, ontology, manager, feedsPowerTo, solarArray01, bat01);
        addObjectPropertyAssertion(factory, ontology, manager, suppliesPowerTo, bat01, pcdu01);
        addObjectPropertyAssertion(factory, ontology, manager, distributesPowerTo, pcdu01, busLine01);
        addObjectPropertyAssertion(factory, ontology, manager, suppliesPowerTo, busLine01, voltReg01);
        addObjectPropertyAssertion(factory, ontology, manager, dependsOn, solarPanel01, solarCell01);
        addObjectPropertyAssertion(factory, ontology, manager, dependsOn, solarArray01, solarPanel01);
        addObjectPropertyAssertion(factory, ontology, manager, dependsOn, bat01, solarArray01);
        addObjectPropertyAssertion(factory, ontology, manager, dependsOn, pcdu01, bat01);
        addObjectPropertyAssertion(factory, ontology, manager, dependsOn, busLine01, pcdu01);
        addObjectPropertyAssertion(factory, ontology, manager, dependsOn, voltReg01, busLine01);
        
        // Voltage Regulator supplies ALL AOCS
                for (OWLNamedIndividual aocsComp : aocsList) {
            addObjectPropertyAssertion(factory, ontology, manager, suppliesPowerTo, voltReg01, aocsComp);
        }

        // --- COMMUNICATION LINKS ---
        addObjectPropertyAssertion(factory, ontology, manager, feedsSignalTo, ant01_comm, lna01);
        addObjectPropertyAssertion(factory, ontology, manager, feedsSignalTo, lna01, filter01);
        addObjectPropertyAssertion(factory, ontology, manager, feedsSignalTo, filter01, dc01);
        addObjectPropertyAssertion(factory, ontology, manager, feedsSignalTo, dc01, demo01);
        addObjectPropertyAssertion(factory, ontology, manager, feedsSignalTo, enc01, uc01);
        addObjectPropertyAssertion(factory, ontology, manager, feedsSignalTo, uc01, amp01);
        addObjectPropertyAssertion(factory, ontology, manager, feedsSignalTo, amp01, ant01_comm);

        addObjectPropertyAssertion(factory, ontology, manager, dependsOn, lna01, ant01_comm);
        addObjectPropertyAssertion(factory, ontology, manager, dependsOn, filter01, lna01);
        addObjectPropertyAssertion(factory, ontology, manager, dependsOn, dc01, filter01);
        addObjectPropertyAssertion(factory, ontology, manager, dependsOn, demo01, dc01);
        addObjectPropertyAssertion(factory, ontology, manager, dependsOn, enc01, uc01);
        addObjectPropertyAssertion(factory, ontology, manager, dependsOn, uc01, amp01);
        addObjectPropertyAssertion(factory, ontology, manager, dependsOn, amp01, ant01_comm);

        addObjectPropertyAssertion(factory, ontology, manager, supports, lo01, dc01);
        addObjectPropertyAssertion(factory, ontology, manager, supports, lo01, uc01);
        addObjectPropertyAssertion(factory, ontology, manager, connectedTo, dupl01, ant01_comm);

        // --- OBC INTEGRATION ---
        OWLNamedIndividual obc01 = createNamedIndividual(factory, ontology, manager, "OBC_01");
        addClassAssertion(factory, ontology, manager, onBoardComputer, obc01);

        // OBC Integration for Communication
        addObjectPropertyAssertion(factory, ontology, manager, feedsSignalTo, demo01, obc01);
        addObjectPropertyAssertion(factory, ontology, manager, feedsSignalTo, obc01, enc01);
        
        // Communication <-> TT&C Bridge
        addObjectPropertyAssertion(factory, ontology, manager, feedsSignalTo, antTtc01, lna01);
        addObjectPropertyAssertion(factory, ontology, manager, feedsSignalTo, amp01, antTtc01);

        for (OWLNamedIndividual c : commComps) {
            addObjectPropertyAssertion(factory, ontology, manager, suppliesPowerTo, voltReg01, c);
        }

        // TT&C Links
        addObjectPropertyAssertion(factory, ontology, manager, feedsCommandTo, gs01, antTtc01);
        addObjectPropertyAssertion(factory, ontology, manager, feedsCommandTo, antTtc01, cr01);
        addObjectPropertyAssertion(factory, ontology, manager, feedsCommandTo, cr01, dec01);
        addObjectPropertyAssertion(factory, ontology, manager, feedsCommandTo, dec01, trk01);
        addObjectPropertyAssertion(factory, ontology, manager, feedsCommandTo, trk01, obc01);

        // --- STRUCTURAL RELATIONS & GLOBAL SUPPORT ---
        // Frame physicallySupports EVERYTHING
        List<OWLNamedIndividual> allCompsToSupport = Arrays.asList(
            obc01, gyro01, str01, gnss01, mag01, sun01, earth01, rw01, mtq01, thr01, ac01, orb01, ant01,
            solarCell01, solarPanel01, solarArray01, bat01, bcc01, pcdu01, busLine01, voltReg01,
            lna01, filter01, dc01, demo01, enc01, uc01, amp01, lo01, dupl01, ant01_comm,
            cr01, dec01, trk01, encTtc01, rad01, antTtc01, gs01,
            mli01, radiator01, heater01, thermo01, sensor01
        );

        for (OWLNamedIndividual c : allCompsToSupport) {
            addObjectPropertyAssertion(factory, ontology, manager, physicallySupports, frame01, c);
        }

        addObjectPropertyAssertion(factory, ontology, manager, mountedOn, panel01, frame01);
        addObjectPropertyAssertion(factory, ontology, manager, mountedOn, antMount01, panel01);
        addObjectPropertyAssertion(factory, ontology, manager, physicallySupports, antMount01, ant01_comm);
        addObjectPropertyAssertion(factory, ontology, manager, physicallySupports, antMount01, antTtc01);

        addObjectPropertyAssertion(factory, ontology, manager, mountedOn, solarMount01, frame01);
        addObjectPropertyAssertion(factory, ontology, manager, physicallySupports, solarMount01, solarArray01);

        addObjectPropertyAssertion(factory, ontology, manager, isolatesVibration, isolation01, frame01);
        addObjectPropertyAssertion(factory, ontology, manager, isolatesVibration, isolation01, rw01);

        addObjectPropertyAssertion(factory, ontology, manager, controls, obc01, deployment01);
        addObjectPropertyAssertion(factory, ontology, manager, dependsOn, ant01_comm, antMount01);
        addObjectPropertyAssertion(factory, ontology, manager, dependsOn, antTtc01, antMount01);
        addObjectPropertyAssertion(factory, ontology, manager, dependsOn, solarArray01, solarMount01);
        addObjectPropertyAssertion(factory, ontology, manager, dependsOn, bat01, frame01);

        addObjectPropertyAssertion(factory, ontology, manager, monitors, obc01, switch01);
        addObjectPropertyAssertion(factory, ontology, manager, monitors, obc01, vibSensor01);
        addObjectPropertyAssertion(factory, ontology, manager, monitors, obc01, posSensor01);
        addObjectPropertyAssertion(factory, ontology, manager, receivesTelemetryFrom, obc01, switch01);
        addObjectPropertyAssertion(factory, ontology, manager, receivesTelemetryFrom, obc01, vibSensor01);
        addObjectPropertyAssertion(factory, ontology, manager, receivesTelemetryFrom, obc01, posSensor01);

        addObjectPropertyAssertion(factory, ontology, manager, feedsTelemetryTo, obc01, encTtc01);
        addObjectPropertyAssertion(factory, ontology, manager, feedsTelemetryTo, encTtc01, rad01);
        addObjectPropertyAssertion(factory, ontology, manager, feedsTelemetryTo, rad01, antTtc01);
        addObjectPropertyAssertion(factory, ontology, manager, feedsTelemetryTo, antTtc01, gs01);

        // Dependency Chains (Fixed for Loop and Direction)
        // Command Chain: Ground -> Antenna -> Receiver -> OBC
        addObjectPropertyAssertion(factory, ontology, manager, dependsOn, antTtc01, gs01);
        addObjectPropertyAssertion(factory, ontology, manager, dependsOn, cr01, antTtc01);
        addObjectPropertyAssertion(factory, ontology, manager, dependsOn, dec01, cr01);
        addObjectPropertyAssertion(factory, ontology, manager, dependsOn, trk01, dec01);
        addObjectPropertyAssertion(factory, ontology, manager, dependsOn, obc01, trk01);

        // Telemetry Chain: OBC -> Encoder -> Radio -> Antenna
        addObjectPropertyAssertion(factory, ontology, manager, dependsOn, encTtc01, obc01);
        addObjectPropertyAssertion(factory, ontology, manager, dependsOn, rad01, encTtc01);
        addObjectPropertyAssertion(factory, ontology, manager, dependsOn, antTtc01, rad01);

        addObjectPropertyAssertion(factory, ontology, manager, receivesCommandFrom, obc01, trk01);
        addObjectPropertyAssertion(factory, ontology, manager, sendsTelemetryTo, obc01, encTtc01);
        
        for (OWLNamedIndividual c : ttcComps) {
            addObjectPropertyAssertion(factory, ontology, manager, suppliesPowerTo, voltReg01, c);
        }

        // --- THERMAL LINKS ---
        addObjectPropertyAssertion(factory, ontology, manager, protects, mli01, bat01);
        addObjectPropertyAssertion(factory, ontology, manager, protects, coating01, solarArray01);
        addObjectPropertyAssertion(factory, ontology, manager, cools, radiator01, amp01);
        addObjectPropertyAssertion(factory, ontology, manager, cools, radiator01, obc01);
        addObjectPropertyAssertion(factory, ontology, manager, transfersHeatTo, path01, radiator01);
        
        addObjectPropertyAssertion(factory, ontology, manager, monitors, sensor01, bat01);
        addObjectPropertyAssertion(factory, ontology, manager, monitors, sensor01, obc01);
        addObjectPropertyAssertion(factory, ontology, manager, monitors, sensor01, amp01);
        
        addObjectPropertyAssertion(factory, ontology, manager, controls, thermo01, heater01);
        addObjectPropertyAssertion(factory, ontology, manager, heats, heater01, bat01);

        for (OWLNamedIndividual c : thermalComps) {
            addObjectPropertyAssertion(factory, ontology, manager, suppliesPowerTo, voltReg01, c);
            addObjectPropertyAssertion(factory, ontology, manager, monitors, obc01, c);
            addObjectPropertyAssertion(factory, ontology, manager, receivesTelemetryFrom, obc01, c);
        }


        List<OWLNamedIndividual> allComps = new HashSet<>(aocsList).stream().collect(Collectors.toList()); // simplify
        allComps = Arrays.asList(gyro01, str01, sun01, mag01, earth01, gnss01, rw01, mtq01, thr01, ac01, orb01, ant01,
                                 solarCell01, solarPanel01, solarArray01, bat01, bcc01, pcdu01, busLine01, voltReg01);
                for (OWLNamedIndividual c : allComps) {
            addObjectPropertyAssertion(factory, ontology, manager, monitors, obc01, c);
            addObjectPropertyAssertion(factory, ontology, manager, receivesTelemetryFrom, obc01, c);
        }

        for (OWLNamedIndividual c : commComps) {
            addObjectPropertyAssertion(factory, ontology, manager, monitors, obc01, c);
            addObjectPropertyAssertion(factory, ontology, manager, receivesTelemetryFrom, obc01, c);
        }


        // --- SWRL RULES ---
        // === FINAL REASONING FIX RULES START ===
        SWRLVariable xVar = factory.getSWRLVariable(IRI.create(BASE_IRI + "x"));
        SWRLVariable yVar = factory.getSWRLVariable(IRI.create(BASE_IRI + "y"));
        SWRLVariable fVar = factory.getSWRLVariable(IRI.create(BASE_IRI + "f"));

        SWRLIndividualArgument safeModeArg = factory.getSWRLIndividualArgument(factory.getOWLNamedIndividual(IRI.create(BASE_IRI + "SafeMode_01")));
        SWRLIndividualArgument heaterOnArg = factory.getSWRLIndividualArgument(factory.getOWLNamedIndividual(IRI.create(BASE_IRI + "HeaterON_01")));
        SWRLIndividualArgument warningActionArg = factory.getSWRLIndividualArgument(factory.getOWLNamedIndividual(IRI.create(BASE_IRI + "Warning_01")));

        // SIGNAL PROPAGATION
        manager.addAxiom(ontology, factory.getSWRLRule(
            Set.of(
                factory.getSWRLObjectPropertyAtom(hasFault, xVar, fVar),
                factory.getSWRLObjectPropertyAtom(feedsSignalTo, xVar, yVar)
            ),
            Set.of(
                factory.getSWRLObjectPropertyAtom(hasFault, yVar, fVar)
            )
        ));

        // COOLING PROPAGATION
        manager.addAxiom(ontology, factory.getSWRLRule(
            Set.of(
                factory.getSWRLObjectPropertyAtom(hasFault, xVar, fVar),
                factory.getSWRLObjectPropertyAtom(cools, xVar, yVar)
            ),
            Set.of(
                factory.getSWRLObjectPropertyAtom(hasFault, yVar, fVar)
            )
        ));

        // HEATING PROPAGATION
        manager.addAxiom(ontology, factory.getSWRLRule(
            Set.of(
                factory.getSWRLObjectPropertyAtom(hasFault, xVar, fVar),
                factory.getSWRLObjectPropertyAtom(heats, xVar, yVar)
            ),
            Set.of(
                factory.getSWRLObjectPropertyAtom(hasFault, yVar, fVar)
            )
        ));

        // MONITORING PROPAGATION (FIXED)
        manager.addAxiom(ontology, factory.getSWRLRule(
            Set.of(
                factory.getSWRLObjectPropertyAtom(hasFault, xVar, fVar),
                factory.getSWRLClassAtom(overheatFault, fVar),
                factory.getSWRLObjectPropertyAtom(monitors, xVar, yVar)
            ),
            Set.of(
                factory.getSWRLObjectPropertyAtom(hasFault, yVar, fVar)
            )
        ));

        // --- STRUCTURE SWRL RULES ---
        // hasFault(?x, ?f) ^ physicallySupports(?x, ?y) -> hasFault(?y, ?f)
        manager.addAxiom(ontology, factory.getSWRLRule(
            Set.of(factory.getSWRLObjectPropertyAtom(hasFault, xVar, fVar), factory.getSWRLObjectPropertyAtom(physicallySupports, xVar, yVar)),
            Set.of(factory.getSWRLObjectPropertyAtom(hasFault, yVar, fVar))
        ));

        // hasFault(?x, ?f) ^ mountedOn(?y, ?x) -> hasFault(?y, ?f)
        manager.addAxiom(ontology, factory.getSWRLRule(
            Set.of(factory.getSWRLObjectPropertyAtom(hasFault, xVar, fVar), factory.getSWRLObjectPropertyAtom(mountedOn, yVar, xVar)),
            Set.of(factory.getSWRLObjectPropertyAtom(hasFault, yVar, fVar))
        ));

        // hasFault(?x, ?f) ^ isolatesVibration(?x, ?y) -> hasFault(?y, ?f)
        manager.addAxiom(ontology, factory.getSWRLRule(
            Set.of(factory.getSWRLObjectPropertyAtom(hasFault, xVar, fVar), factory.getSWRLObjectPropertyAtom(isolatesVibration, xVar, yVar)),
            Set.of(factory.getSWRLObjectPropertyAtom(hasFault, yVar, fVar))
        ));

        // Global Structural Impact: hasFault(?x, StructuralFailure) ^ physicallySupports(?x, ?y) -> hasFault(?y, StructuralFailure)
        manager.addAxiom(ontology, factory.getSWRLRule(
            Set.of(factory.getSWRLObjectPropertyAtom(hasFault, xVar, fVar), factory.getSWRLClassAtom(structuralFailure, fVar), factory.getSWRLObjectPropertyAtom(physicallySupports, xVar, yVar)),
            Set.of(factory.getSWRLObjectPropertyAtom(hasFault, yVar, fVar))
        ));

        // OBC ACTION RULES (STRUCTURE)
        // StructuralFailure -> SafeMode
        manager.addAxiom(ontology, factory.getSWRLRule(
            Set.of(factory.getSWRLObjectPropertyAtom(hasFault, xVar, fVar), factory.getSWRLClassAtom(structuralFailure, fVar)),
            Set.of(factory.getSWRLObjectPropertyAtom(triggersAction, fVar, safeModeArg))
        ));
        // DeploymentFailure -> Warning
        manager.addAxiom(ontology, factory.getSWRLRule(
            Set.of(factory.getSWRLObjectPropertyAtom(hasFault, xVar, fVar), factory.getSWRLClassAtom(deploymentFailure, fVar)),
            Set.of(factory.getSWRLObjectPropertyAtom(triggersAction, fVar, warningActionArg))
        ));
        // VibrationFailure -> Warning
        manager.addAxiom(ontology, factory.getSWRLRule(
            Set.of(factory.getSWRLObjectPropertyAtom(hasFault, xVar, fVar), factory.getSWRLClassAtom(vibrationFailure, fVar)),
            Set.of(factory.getSWRLObjectPropertyAtom(triggersAction, fVar, warningActionArg))
        ));

        // OBC ACTION RULES (FIXED)
        // Overheat → SafeMode
        manager.addAxiom(ontology, factory.getSWRLRule(
            Set.of(
                factory.getSWRLObjectPropertyAtom(hasFault, xVar, fVar),
                factory.getSWRLClassAtom(overheatFault, fVar)
            ),
            Set.of(
                factory.getSWRLObjectPropertyAtom(triggersAction, fVar, safeModeArg)
            )
        ));

        // Underheat → HeaterON
        manager.addAxiom(ontology, factory.getSWRLRule(
            Set.of(
                factory.getSWRLObjectPropertyAtom(hasFault, xVar, fVar),
                factory.getSWRLClassAtom(underheatFault, fVar)
            ),
            Set.of(
                factory.getSWRLObjectPropertyAtom(triggersAction, fVar, heaterOnArg)
            )
        ));

        // SensorFault → Warning
        manager.addAxiom(ontology, factory.getSWRLRule(
            Set.of(
                factory.getSWRLObjectPropertyAtom(hasFault, xVar, fVar),
                factory.getSWRLClassAtom(sensorFault, fVar)
            ),
            Set.of(
                factory.getSWRLObjectPropertyAtom(triggersAction, fVar, warningActionArg)
            )
        ));

        // === FINAL REASONING FIX RULES END ===
        // Basic Detection (Individual-Based)
        addSWRLRuleIndividual(factory, ontology, manager, gyroscope, hasAngularRate, SWRLBuiltInsVocabulary.GREATER_THAN.getIRI(), 10.0, hasFault, fGyro);
        addSWRLRuleIndividual(factory, ontology, manager, gnssReceiver, hasPositionError, SWRLBuiltInsVocabulary.GREATER_THAN.getIRI(), 20.0, hasFault, fGnss);
        addSWRLRuleIndividual(factory, ontology, manager, starTracker, hasTrackingError, SWRLBuiltInsVocabulary.GREATER_THAN.getIRI(), 1.0, hasFault, fStar);
        addSWRLRuleIndividual(factory, ontology, manager, sunSensor, hasSunVectorError, SWRLBuiltInsVocabulary.GREATER_THAN.getIRI(), 1.0, hasFault, fSun);
        addSWRLRuleIndividual(factory, ontology, manager, magnetometer, hasMagneticFieldError, SWRLBuiltInsVocabulary.EQUAL.getIRI(), 0.0, hasFault, fMag);
        addSWRLRuleIndividual(factory, ontology, manager, earthSensor, hasEarthVectorError, SWRLBuiltInsVocabulary.GREATER_THAN.getIRI(), 1.0, hasFault, fEarth);
        addSWRLRuleIndividual(factory, ontology, manager, reactionWheel, hasVibration, SWRLBuiltInsVocabulary.GREATER_THAN.getIRI(), 5.0, hasFault, fRw);
        addSWRLRuleIndividual(factory, ontology, manager, magnetorquer, hasMagneticMoment, SWRLBuiltInsVocabulary.EQUAL.getIRI(), 0.0, hasFault, fMtq);
        addSWRLRuleIndividual(factory, ontology, manager, thruster, hasThrust, SWRLBuiltInsVocabulary.EQUAL.getIRI(), 0.0, hasFault, fThr);
        addSWRLRuleIndividual(factory, ontology, manager, attitudeControl, hasAttitudeError, SWRLBuiltInsVocabulary.GREATER_THAN.getIRI(), 10.0, hasFault, fAc);
        addSWRLRuleIndividual(factory, ontology, manager, orbitControl, hasOrbitDeviation, SWRLBuiltInsVocabulary.GREATER_THAN.getIRI(), 2.0, hasFault, fOrb);
        addSWRLRuleIndividual(factory, ontology, manager, antennaPointing, hasPointingError, SWRLBuiltInsVocabulary.GREATER_THAN.getIRI(), 3.0, hasFault, fAnt);

        // Power Detection
        addSWRLRuleIndividual(factory, ontology, manager, solarArrayClass, hasGeneratedPower, SWRLBuiltInsVocabulary.LESS_THAN.getIRI(), 100.0, hasFault, fSolar);
        addSWRLRuleIndividual(factory, ontology, manager, batteryClass, hasChargeLevel, SWRLBuiltInsVocabulary.LESS_THAN.getIRI(), 20.0, hasFault, fBat);
        addSWRLRuleIndividual(factory, ontology, manager, batteryClass, hasTemperature, SWRLBuiltInsVocabulary.GREATER_THAN.getIRI(), 50.0, hasFault, fBat);
        addSWRLRuleIndividual(factory, ontology, manager, bccClass, hasChargeRate, SWRLBuiltInsVocabulary.EQUAL.getIRI(), 0.0, hasFault, fBcc);
        addSWRLRuleIndividual(factory, ontology, manager, pcduClass, hasOutputVoltage, SWRLBuiltInsVocabulary.LESS_THAN.getIRI(), 20.0, hasFault, fPcdu);
        addSWRLRuleIndividual(factory, ontology, manager, pcduClass, hasDistributionLoad, SWRLBuiltInsVocabulary.GREATER_THAN.getIRI(), 90.0, hasFault, fPcdu);
        addSWRLRuleIndividual(factory, ontology, manager, busLineClass, hasBusVoltage, SWRLBuiltInsVocabulary.LESS_THAN.getIRI(), 28.0, hasFault, fBus);
        addSWRLRuleIndividual(factory, ontology, manager, voltRegClass, hasRegulatedVoltage, SWRLBuiltInsVocabulary.LESS_THAN.getIRI(), 5.0, hasFault, fReg);

        // Propagation: General
        SWRLVariable cVar = factory.getSWRLVariable(IRI.create(BASE_IRI + "c"));
        SWRLVariable f1Var = factory.getSWRLVariable(IRI.create(BASE_IRI + "f1"));
        SWRLVariable f2Var = factory.getSWRLVariable(IRI.create(BASE_IRI + "f2"));
        manager.addAxiom(ontology, factory.getSWRLRule(new HashSet<>(Arrays.asList(factory.getSWRLObjectPropertyAtom(hasFault, cVar, f1Var), factory.getSWRLObjectPropertyAtom(propagatesTo, f1Var, f2Var))), new HashSet<>(Arrays.asList(factory.getSWRLObjectPropertyAtom(hasFault, cVar, f2Var)))));

        // Propagation: Functional Dependencies (Power Supply)
        SWRLVariable xVar2 = factory.getSWRLVariable(IRI.create(BASE_IRI + "x2"));
        SWRLVariable yVar2 = factory.getSWRLVariable(IRI.create(BASE_IRI + "y2"));
        // hasFault(?x2, ?f1) ^ suppliesPowerTo(?x2, ?y2) -> hasFault(?y2, ?f1)
        manager.addAxiom(ontology, factory.getSWRLRule(new HashSet<>(Arrays.asList(factory.getSWRLObjectPropertyAtom(hasFault, xVar2, f1Var), factory.getSWRLObjectPropertyAtom(suppliesPowerTo, xVar2, yVar2))), new HashSet<>(Arrays.asList(factory.getSWRLObjectPropertyAtom(hasFault, yVar2, f1Var)))));

        // Propagation: Sensor -> Control (feedsDataTo)
        // hasFault(?s, ?f) ^ feedsDataTo(?s, ?c) -> hasFault(?c, ?f)
        SWRLVariable sVar_sensor = factory.getSWRLVariable(IRI.create(BASE_IRI + "sensor"));
        SWRLVariable cVar_control = factory.getSWRLVariable(IRI.create(BASE_IRI + "control"));
        SWRLVariable fVar_prop = factory.getSWRLVariable(IRI.create(BASE_IRI + "fprop"));
        manager.addAxiom(ontology, factory.getSWRLRule(new HashSet<>(Arrays.asList(factory.getSWRLObjectPropertyAtom(hasFault, sVar_sensor, fVar_prop), factory.getSWRLObjectPropertyAtom(feedsDataTo, sVar_sensor, cVar_control))), new HashSet<>(Arrays.asList(factory.getSWRLObjectPropertyAtom(hasFault, cVar_control, fVar_prop)))));

        // Propagation: Control -> Actuator (sendsCommandTo)
        // hasFault(?c, ?f) ^ sendsCommandTo(?c, ?a) -> hasFault(?a, ?f)
        SWRLVariable aVar_actuator = factory.getSWRLVariable(IRI.create(BASE_IRI + "actuator"));
        manager.addAxiom(ontology, factory.getSWRLRule(new HashSet<>(Arrays.asList(factory.getSWRLObjectPropertyAtom(hasFault, cVar_control, fVar_prop), factory.getSWRLObjectPropertyAtom(sendsCommandTo, cVar_control, aVar_actuator))), new HashSet<>(Arrays.asList(factory.getSWRLObjectPropertyAtom(hasFault, aVar_actuator, fVar_prop)))));

        // Power Propagation Chains
        addObjectPropertyAssertion(factory, ontology, manager, propagatesTo, fSolar, fBat);
        addObjectPropertyAssertion(factory, ontology, manager, propagatesTo, fBat, fPcdu);
        addObjectPropertyAssertion(factory, ontology, manager, propagatesTo, fPcdu, fBus);
        addObjectPropertyAssertion(factory, ontology, manager, propagatesTo, fBus, fReg);

        // Cross-Subsystem Propagation (Now handled by SWRL rules)
        // Transmitter effects handled via SWRL rules instead of hardcoded assertions

        // Affects Component (Internal Logic)
        SWRLVariable fVar2 = factory.getSWRLVariable(IRI.create(BASE_IRI + "f2"));
        manager.addAxiom(ontology, factory.getSWRLRule(new HashSet<>(Arrays.asList(factory.getSWRLObjectPropertyAtom(hasFault, cVar, fVar2))), new HashSet<>(Arrays.asList(factory.getSWRLObjectPropertyAtom(affectsComponent, fVar2, cVar)))));

        // OBC SafeMode Trigger
        OWLNamedIndividual safeModeAction = createNamedIndividual(factory, ontology, manager, "SafeMode");
        OWLNamedIndividual powerSaveMode = createNamedIndividual(factory, ontology, manager, "PowerSaveMode");
        OWLNamedIndividual warningActionFallback = createNamedIndividual(factory, ontology, manager, "Warning");
        addClassAssertion(factory, ontology, manager, action, safeModeAction);
        addClassAssertion(factory, ontology, manager, action, powerSaveMode);
        addClassAssertion(factory, ontology, manager, action, warningActionFallback);
        SWRLVariable sVar = factory.getSWRLVariable(IRI.create(BASE_IRI + "s"));
        manager.addAxiom(ontology, factory.getSWRLRule(new HashSet<>(Arrays.asList(factory.getSWRLClassAtom(fault, fVar2), factory.getSWRLDataPropertyAtom(hasSeverity, fVar2, sVar), factory.getSWRLBuiltInAtom(SWRLBuiltInsVocabulary.EQUAL.getIRI(), Arrays.asList(sVar, factory.getSWRLLiteralArgument(factory.getOWLLiteral("HIGH")))))), new HashSet<>(Arrays.asList(factory.getSWRLObjectPropertyAtom(triggersAction, fVar2, factory.getSWRLIndividualArgument(safeModeAction))))));

        // --- NEW SWRL RULES FOR EXTENDED SYSTEM ---

        // STEP 1: Solar Degradation and Irradiance Detection
        // IF irradiance < 800 → SolarFault
        addSWRLRuleIndividual(factory, ontology, manager, solarArrayClass, hasIrradiance, SWRLBuiltInsVocabulary.LESS_THAN.getIRI(), 800.0, hasFault, fSolar);
        // IF degradation > 0.95 → SolarFault
        addSWRLRuleIndividual(factory, ontology, manager, solarPanelClass, hasDegradationRate, SWRLBuiltInsVocabulary.GREATER_THAN.getIRI(), 0.95, hasFault, fSolar);

        // STEP 2: Battery Discharge Rate and Cycle Count Detection
        // IF dischargeRate > 40 → BatteryFault
        addSWRLRuleIndividual(factory, ontology, manager, batteryClass, hasDischargeRate, SWRLBuiltInsVocabulary.GREATER_THAN.getIRI(), 40.0, hasFault, fBat);
        // IF temperature > 60 → BatteryFault (Critical)
        addSWRLRuleIndividual(factory, ontology, manager, batteryClass, hasTemperature, SWRLBuiltInsVocabulary.GREATER_THAN.getIRI(), 60.0, hasFault, fBat);
        // IF cycleCount > 5000 → BatteryFault
        addSWRLRuleIndividual(factory, ontology, manager, batteryClass, hasCycleCount, SWRLBuiltInsVocabulary.GREATER_THAN.getIRI(), 5000.0, hasFault, fBat);

        // STEP 3: PCDU Load Distribution Balance Detection
        // IF loadDistributionBalance < 0.5 → PCDUFault
        addSWRLRuleIndividual(factory, ontology, manager, pcduClass, hasLoadDistributionBalance, SWRLBuiltInsVocabulary.LESS_THAN.getIRI(), 0.5, hasFault, fPcdu);

        // STEP 4: Dependency Propagation Rule
        // hasFault(?x3, ?f3) ^ dependsOn(?y3, ?x3) -> hasFault(?y3, ?f3)
        SWRLVariable xVar3 = factory.getSWRLVariable(IRI.create(BASE_IRI + "x3"));
        SWRLVariable yVar3 = factory.getSWRLVariable(IRI.create(BASE_IRI + "y3"));
        SWRLVariable fVar3 = factory.getSWRLVariable(IRI.create(BASE_IRI + "f3"));
        manager.addAxiom(ontology, factory.getSWRLRule(new HashSet<>(Arrays.asList(factory.getSWRLObjectPropertyAtom(hasFault, xVar3, fVar3), factory.getSWRLObjectPropertyAtom(dependsOn, yVar3, xVar3))), new HashSet<>(Arrays.asList(factory.getSWRLObjectPropertyAtom(hasFault, yVar3, fVar3)))));

        // STEP 5: Cross-Subsystem Propagation
        // hasFault(?p, RegulatorFault_001) ^ suppliesPowerTo(?p, ?c) -> hasFault(?c, RegulatorFault_001)
        SWRLVariable pVar = factory.getSWRLVariable(IRI.create(BASE_IRI + "p"));
        SWRLVariable cVar2 = factory.getSWRLVariable(IRI.create(BASE_IRI + "c2"));
        SWRLIndividualArgument regFaultArg = factory.getSWRLIndividualArgument(fReg);
        manager.addAxiom(ontology, factory.getSWRLRule(new HashSet<>(Arrays.asList(factory.getSWRLObjectPropertyAtom(hasFault, pVar, regFaultArg), factory.getSWRLObjectPropertyAtom(suppliesPowerTo, pVar, cVar2))), new HashSet<>(Arrays.asList(factory.getSWRLObjectPropertyAtom(hasFault, cVar2, regFaultArg)))));

        // STEP 8: Multi-Fault Detection - BatteryFault + SolarFault with dependency → CriticalPowerFailure
        // hasFault(?solar, SolarFault_001) ^ hasFault(?battery, BatteryFault_001) ^ dependsOn(?battery, ?solar) -> CriticalPowerFailure(?battery)
        SWRLVariable solarVar = factory.getSWRLVariable(IRI.create(BASE_IRI + "solar"));
        SWRLVariable batteryVar = factory.getSWRLVariable(IRI.create(BASE_IRI + "battery"));
        SWRLIndividualArgument batFaultArg = factory.getSWRLIndividualArgument(fBat);
        SWRLIndividualArgument solarFaultArg = factory.getSWRLIndividualArgument(fSolar);
        manager.addAxiom(ontology, factory.getSWRLRule(new HashSet<>(Arrays.asList(factory.getSWRLObjectPropertyAtom(hasFault, solarVar, solarFaultArg), factory.getSWRLObjectPropertyAtom(hasFault, batteryVar, batFaultArg), factory.getSWRLObjectPropertyAtom(dependsOn, batteryVar, solarVar))), new HashSet<>(Arrays.asList(factory.getSWRLClassAtom(criticalPowerFailure, batteryVar)))));

        // STEP 9: OBC Decision Levels - MEDIUM severity -> PowerSaveMode
        // hasFault(?f, ?severity) ^ EQUAL(severity, "MEDIUM") -> triggersAction(?f, PowerSaveMode)
        SWRLVariable mediumSeverity = factory.getSWRLVariable(IRI.create(BASE_IRI + "mediumSev"));
        manager.addAxiom(ontology, factory.getSWRLRule(new HashSet<>(Arrays.asList(factory.getSWRLClassAtom(fault, fVar), factory.getSWRLDataPropertyAtom(hasSeverity, fVar, mediumSeverity), factory.getSWRLBuiltInAtom(SWRLBuiltInsVocabulary.EQUAL.getIRI(), Arrays.asList(mediumSeverity, factory.getSWRLLiteralArgument(factory.getOWLLiteral("MEDIUM")))))), new HashSet<>(Arrays.asList(factory.getSWRLObjectPropertyAtom(triggersAction, fVar, factory.getSWRLIndividualArgument(powerSaveMode))))));

        // STEP 9: OBC Decision Levels - LOW severity -> Warning
        // hasFault(?f, ?severity) ^ EQUAL(severity, "LOW") -> triggersAction(?f, Warning)
        SWRLVariable lowSeverity = factory.getSWRLVariable(IRI.create(BASE_IRI + "lowSev"));
        manager.addAxiom(ontology, factory.getSWRLRule(new HashSet<>(Arrays.asList(factory.getSWRLClassAtom(fault, fVar), factory.getSWRLDataPropertyAtom(hasSeverity, fVar, lowSeverity), factory.getSWRLBuiltInAtom(SWRLBuiltInsVocabulary.EQUAL.getIRI(), Arrays.asList(lowSeverity, factory.getSWRLLiteralArgument(factory.getOWLLiteral("LOW")))))), new HashSet<>(Arrays.asList(factory.getSWRLObjectPropertyAtom(triggersAction, fVar, factory.getSWRLIndividualArgument(warningAction))))));

        // STEP 10: Root Cause Detection via propagatesTo chain
        // Faults propagate in order: SolarFault -> BatteryFault -> PCDUFault -> BusFault -> RegulatorFault
        // This chain is established via propagatesTo assertions earlier

        // STEP 11: System Priority Integration
        // belongsToSubsystem(?c, ?sub) ^ hasPriority(?sub, "HIGH") -> HIGH priority component
        // Priorities already assigned to subsystems

        // Additional rule: Affects Component derivation via power supply chain (SWRL-based)
        // hasFault(?x4, ?f4) ^ suppliesPowerTo(?x4, ?y4) -> affectsComponent(?f4, ?y4)
        SWRLVariable xVar4 = factory.getSWRLVariable(IRI.create(BASE_IRI + "x4"));
        SWRLVariable yVar4 = factory.getSWRLVariable(IRI.create(BASE_IRI + "y4"));
        SWRLVariable fVar4 = factory.getSWRLVariable(IRI.create(BASE_IRI + "f4"));
        manager.addAxiom(ontology, factory.getSWRLRule(new HashSet<>(Arrays.asList(factory.getSWRLObjectPropertyAtom(hasFault, xVar4, fVar4), factory.getSWRLObjectPropertyAtom(suppliesPowerTo, xVar4, yVar4))), new HashSet<>(Arrays.asList(factory.getSWRLObjectPropertyAtom(affectsComponent, fVar4, yVar4)))));

        // === COMMUNICATION DETECTION RULES START ===
        addSWRLRuleIndividual(factory, ontology, manager, lnaClass, hasGain, SWRLBuiltInsVocabulary.LESS_THAN.getIRI(), 10.0, hasFault, fLna);
        addSWRLRuleIndividual(factory, ontology, manager, filterClass, hasBandwidth, SWRLBuiltInsVocabulary.LESS_THAN.getIRI(), 2.0, hasFault, fFilter);
        addSWRLRuleIndividual(factory, ontology, manager, downconverterClass, hasFrequencyShift, SWRLBuiltInsVocabulary.EQUAL.getIRI(), 0.0, hasFault, fDc);
        addSWRLRuleIndividual(factory, ontology, manager, demodulatorClass, hasBitErrorRate, SWRLBuiltInsVocabulary.GREATER_THAN.getIRI(), 0.1, hasFault, fDemo);
        addSWRLRuleIndividual(factory, ontology, manager, encoderClass, hasEncodingRate, SWRLBuiltInsVocabulary.EQUAL.getIRI(), 0.0, hasFault, fEnc);
        addSWRLRuleIndividual(factory, ontology, manager, upconverterClass, hasCarrierFrequency, SWRLBuiltInsVocabulary.EQUAL.getIRI(), 0.0, hasFault, fUc);
        addSWRLRuleIndividual(factory, ontology, manager, powerAmpClass, hasOutputPower, SWRLBuiltInsVocabulary.LESS_THAN.getIRI(), 5.0, hasFault, fAmp);
        addSWRLRuleIndividual(factory, ontology, manager, loClass, hasFrequencyStability, SWRLBuiltInsVocabulary.LESS_THAN.getIRI(), 1.0, hasFault, fLo);
        addSWRLRuleIndividual(factory, ontology, manager, duplexerClass, hasIsolation, SWRLBuiltInsVocabulary.LESS_THAN.getIRI(), 10.0, hasFault, fDupl);
        addSWRLRuleIndividual(factory, ontology, manager, antennaClass, hasSignalStrength, SWRLBuiltInsVocabulary.LESS_THAN.getIRI(), 20.0, hasFault, fAntComm);
        // === COMMUNICATION DETECTION RULES END ===

        // === COMMUNICATION PROPAGATION RULES START ===
        // Signal Flow Propagation
        manager.addAxiom(ontology, factory.getSWRLRule(
            Set.of(
                factory.getSWRLObjectPropertyAtom(hasFault, xVar, fVar),
                factory.getSWRLObjectPropertyAtom(feedsSignalTo, xVar, yVar)
            ),
            Set.of(
                factory.getSWRLObjectPropertyAtom(hasFault, yVar, fVar)
            )
        ));

        // RF Support Propagation (LO supports Mixer/Upconverter)
        SWRLIndividualArgument loFaultArgRule = factory.getSWRLIndividualArgument(fLo);
        manager.addAxiom(ontology, factory.getSWRLRule(
            Set.of(
                factory.getSWRLObjectPropertyAtom(hasFault, xVar, loFaultArgRule),
                factory.getSWRLObjectPropertyAtom(supports, xVar, yVar)
            ),
            Set.of(
                factory.getSWRLObjectPropertyAtom(hasFault, yVar, loFaultArgRule)
            )
        ));

        // Duplexer Connection Propagation
        manager.addAxiom(ontology, factory.getSWRLRule(
            Set.of(
                factory.getSWRLObjectPropertyAtom(hasFault, xVar, fVar),
                factory.getSWRLObjectPropertyAtom(connectedTo, xVar, yVar)
            ),
            Set.of(
                factory.getSWRLObjectPropertyAtom(hasFault, yVar, fVar)
            )
        ));
        // === COMMUNICATION PROPAGATION RULES END ===

        // --- END OF NEW RULES ---

        // === TT&C DETECTION RULES START ===
        addSWRLRuleIndividual(factory, ontology, manager, commandReceiverClass, hasSignalStrength, SWRLBuiltInsVocabulary.LESS_THAN.getIRI(), 20.0, hasFault, fCr);
        addSWRLRuleIndividual(factory, ontology, manager, decoderClass, hasDecodingError, SWRLBuiltInsVocabulary.GREATER_THAN.getIRI(), 0.1, hasFault, fDec);
        addSWRLRuleIndividual(factory, ontology, manager, trackingUnitClass, hasTrackingAccuracy, SWRLBuiltInsVocabulary.LESS_THAN.getIRI(), 0.8, hasFault, fTrk);
        addSWRLRuleIndividual(factory, ontology, manager, telemetryEncoderClass, hasEncodingQuality, SWRLBuiltInsVocabulary.EQUAL.getIRI(), 0.0, hasFault, fEncTtc);
        addSWRLRuleIndividual(factory, ontology, manager, ttcRadioClass, hasTransmitPower, SWRLBuiltInsVocabulary.LESS_THAN.getIRI(), 5.0, hasFault, fRad);
        addSWRLRuleIndividual(factory, ontology, manager, ttcAntennaClass, hasSignalStrength, SWRLBuiltInsVocabulary.LESS_THAN.getIRI(), 20.0, hasFault, fAntTtc);
        addSWRLRuleIndividual(factory, ontology, manager, groundStationClass, hasLinkQuality, SWRLBuiltInsVocabulary.LESS_THAN.getIRI(), 0.5, hasFault, fGnd);
        // === TT&C DETECTION RULES END ===

        // === TT&C PROPAGATION RULES START ===
        manager.addAxiom(ontology, factory.getSWRLRule(
            Set.of(
                factory.getSWRLObjectPropertyAtom(hasFault, xVar, fVar),
                factory.getSWRLObjectPropertyAtom(feedsCommandTo, xVar, yVar)
            ),
            Set.of(
                factory.getSWRLObjectPropertyAtom(hasFault, yVar, fVar)
            )
        ));

        manager.addAxiom(ontology, factory.getSWRLRule(
            Set.of(
                factory.getSWRLObjectPropertyAtom(hasFault, xVar, fVar),
                factory.getSWRLObjectPropertyAtom(feedsTelemetryTo, xVar, yVar)
            ),
            Set.of(
                factory.getSWRLObjectPropertyAtom(hasFault, yVar, fVar)
            )
        ));
        // === TT&C PROPAGATION RULES END ===

        // Ground Link Failure Impact (Propagates to Antenna then Receiver Chain)
        SWRLVariable gsVar = factory.getSWRLVariable(IRI.create(BASE_IRI + "gs"));
        SWRLVariable antVarTtcRule = factory.getSWRLVariable(IRI.create(BASE_IRI + "antTtcRule"));
        SWRLVariable recVarTtcRule = factory.getSWRLVariable(IRI.create(BASE_IRI + "recTtcRule"));
        SWRLIndividualArgument gndFaultArg = factory.getSWRLIndividualArgument(fGnd);
        
        // GroundLinkFault -> affects TTCAntenna
        manager.addAxiom(ontology, factory.getSWRLRule(
            new HashSet<>(Arrays.asList(factory.getSWRLObjectPropertyAtom(hasFault, gsVar, gndFaultArg), factory.getSWRLObjectPropertyAtom(feedsCommandTo, gsVar, antVarTtcRule))),
            new HashSet<>(Arrays.asList(factory.getSWRLObjectPropertyAtom(hasFault, antVarTtcRule, gndFaultArg)))
        ));

        // TTCAntenna -> affects receiver chain (CommandReceiver)
        manager.addAxiom(ontology, factory.getSWRLRule(
            new HashSet<>(Arrays.asList(factory.getSWRLObjectPropertyAtom(hasFault, antVarTtcRule, gndFaultArg), factory.getSWRLObjectPropertyAtom(feedsCommandTo, antVarTtcRule, recVarTtcRule))),
            new HashSet<>(Arrays.asList(factory.getSWRLObjectPropertyAtom(hasFault, recVarTtcRule, gndFaultArg)))
        ));

        // === THERMAL DETECTION RULES START ===
        addSWRLRuleIndividual(factory, ontology, manager, component, hasTemperature, SWRLBuiltInsVocabulary.GREATER_THAN.getIRI(), 100.0, hasFault, fOverheat);
        addSWRLRuleIndividual(factory, ontology, manager, component, hasTemperature, SWRLBuiltInsVocabulary.LESS_THAN.getIRI(), 0.0, hasFault, fUnderheat);
        addSWRLRuleIndividual(factory, ontology, manager, tempSensorClass, hasSensorAccuracy, SWRLBuiltInsVocabulary.LESS_THAN.getIRI(), 0.5, hasFault, fSensor);
        addSWRLRuleIndividual(factory, ontology, manager, heaterClass, hasHeatLevel, SWRLBuiltInsVocabulary.EQUAL.getIRI(), 0.0, hasFault, fHeater);
        // === THERMAL DETECTION RULES END ===

        // === THERMAL PROPAGATION RULES START ===
        // Rule 1: Overheat Propagation
        manager.addAxiom(ontology, factory.getSWRLRule(
            Set.of(
                factory.getSWRLObjectPropertyAtom(hasFault, xVar, factory.getSWRLIndividualArgument(fOverheat)),
                factory.getSWRLObjectPropertyAtom(affectsComponent, factory.getSWRLIndividualArgument(fOverheat), yVar)
            ),
            Set.of(
                factory.getSWRLObjectPropertyAtom(hasFault, yVar, factory.getSWRLIndividualArgument(fOverheat))
            )
        ));

        // Rule 2: Cooling Failure
        manager.addAxiom(ontology, factory.getSWRLRule(
            Set.of(
                factory.getSWRLObjectPropertyAtom(hasFault, xVar, fVar),
                factory.getSWRLObjectPropertyAtom(cools, xVar, yVar)
            ),
            Set.of(
                factory.getSWRLObjectPropertyAtom(hasFault, yVar, fVar)
            )
        ));

        // Rule 3: Heating Failure
        manager.addAxiom(ontology, factory.getSWRLRule(
            Set.of(
                factory.getSWRLObjectPropertyAtom(hasFault, xVar, fVar),
                factory.getSWRLObjectPropertyAtom(heats, xVar, yVar)
            ),
            Set.of(
                factory.getSWRLObjectPropertyAtom(hasFault, yVar, fVar)
            )
        ));
        // === THERMAL PROPAGATION RULES END ===

        // === CROSS-SUBSYSTEM THERMAL IMPACT ===
        // Overheat affects critical components
        addObjectPropertyAssertion(factory, ontology, manager, affectsComponent, fOverheat, voltReg01);
        addObjectPropertyAssertion(factory, ontology, manager, affectsComponent, fOverheat, amp01);
        addObjectPropertyAssertion(factory, ontology, manager, affectsComponent, fOverheat, obc01);

        // Cross-Subsystem: TT&C fault -> Communication degraded
        SWRLIndividualArgument commSubArg = factory.getSWRLIndividualArgument(subComm);
        SWRLIndividualArgument ttcSubArg = factory.getSWRLIndividualArgument(subTtc);
        manager.addAxiom(ontology, factory.getSWRLRule(
            Set.of(
                factory.getSWRLObjectPropertyAtom(hasFault, xVar, fVar),
                factory.getSWRLObjectPropertyAtom(belongsToSubsystem, xVar, ttcSubArg)
            ),
            Set.of(
                factory.getSWRLObjectPropertyAtom(hasFault, commSubArg, fVar)
            )
        ));

        // === OBC THERMAL ACTIONS ===
        // Rule: Overheat -> SafeMode
        manager.addAxiom(ontology, factory.getSWRLRule(
            Set.of(factory.getSWRLObjectPropertyAtom(hasFault, xVar, factory.getSWRLIndividualArgument(fOverheat))),
            Set.of(factory.getSWRLObjectPropertyAtom(triggersAction, factory.getSWRLIndividualArgument(fOverheat), factory.getSWRLIndividualArgument(safeMode)))
        ));

        // Rule: Underheat -> Heater ON
        manager.addAxiom(ontology, factory.getSWRLRule(
            Set.of(factory.getSWRLObjectPropertyAtom(hasFault, xVar, factory.getSWRLIndividualArgument(fUnderheat))),
            Set.of(factory.getSWRLObjectPropertyAtom(triggersAction, factory.getSWRLIndividualArgument(fUnderheat), factory.getSWRLIndividualArgument(heaterOn)))
        ));

        // Rule: SensorFault -> Warning
        manager.addAxiom(ontology, factory.getSWRLRule(
            Set.of(factory.getSWRLObjectPropertyAtom(hasFault, xVar, factory.getSWRLIndividualArgument(fSensor))),
            Set.of(factory.getSWRLObjectPropertyAtom(triggersAction, factory.getSWRLIndividualArgument(fSensor), factory.getSWRLIndividualArgument(warningAction)))
        ));

        // === FINAL GLOBAL PROPAGATION RULES START ===
        // Rule 1: Signal Flow Propagation (Communication & OBC)
        manager.addAxiom(ontology, factory.getSWRLRule(
            Set.of(factory.getSWRLObjectPropertyAtom(hasFault, xVar, fVar), factory.getSWRLObjectPropertyAtom(feedsSignalTo, xVar, yVar)),
            Set.of(factory.getSWRLObjectPropertyAtom(hasFault, yVar, fVar))
        ));

        // Rule 2: Dependency Propagation (Global)
        manager.addAxiom(ontology, factory.getSWRLRule(
            Set.of(factory.getSWRLObjectPropertyAtom(hasFault, xVar, fVar), factory.getSWRLObjectPropertyAtom(dependsOn, yVar, xVar)),
            Set.of(factory.getSWRLObjectPropertyAtom(hasFault, yVar, fVar))
        ));

        // Rule 3: Power Propagation (Global)
        manager.addAxiom(ontology, factory.getSWRLRule(
            Set.of(factory.getSWRLObjectPropertyAtom(hasFault, xVar, fVar), factory.getSWRLObjectPropertyAtom(suppliesPowerTo, xVar, yVar)),
            Set.of(factory.getSWRLObjectPropertyAtom(hasFault, yVar, fVar))
        ));

        // Rule 4: Thermal Propagation
        // Cooling failure
        manager.addAxiom(ontology, factory.getSWRLRule(
            Set.of(factory.getSWRLObjectPropertyAtom(hasFault, xVar, fVar), factory.getSWRLObjectPropertyAtom(cools, xVar, yVar)),
            Set.of(factory.getSWRLObjectPropertyAtom(hasFault, yVar, fVar))
        ));
        // Heating failure
        manager.addAxiom(ontology, factory.getSWRLRule(
            Set.of(factory.getSWRLObjectPropertyAtom(hasFault, xVar, fVar), factory.getSWRLObjectPropertyAtom(heats, xVar, yVar)),
            Set.of(factory.getSWRLObjectPropertyAtom(hasFault, yVar, fVar))
        ));
        // Overheat spread via monitors (Thermal Sensors)
        manager.addAxiom(ontology, factory.getSWRLRule(
            Set.of(factory.getSWRLObjectPropertyAtom(hasFault, xVar, factory.getSWRLIndividualArgument(fOverheat)), factory.getSWRLObjectPropertyAtom(monitors, xVar, yVar)),
            Set.of(factory.getSWRLObjectPropertyAtom(hasFault, yVar, factory.getSWRLIndividualArgument(fOverheat)))
        ));

        // Rule 5: TT&C Propagation (Command & Telemetry Chains)
        manager.addAxiom(ontology, factory.getSWRLRule(
            Set.of(factory.getSWRLObjectPropertyAtom(hasFault, xVar, fVar), factory.getSWRLObjectPropertyAtom(feedsCommandTo, xVar, yVar)),
            Set.of(factory.getSWRLObjectPropertyAtom(hasFault, yVar, fVar))
        ));
        manager.addAxiom(ontology, factory.getSWRLRule(
            Set.of(factory.getSWRLObjectPropertyAtom(hasFault, xVar, fVar), factory.getSWRLObjectPropertyAtom(feedsTelemetryTo, xVar, yVar)),
            Set.of(factory.getSWRLObjectPropertyAtom(hasFault, yVar, fVar))
        ));

        // Rule 6: RF Support Propagation (Local Oscillator failure)
        manager.addAxiom(ontology, factory.getSWRLRule(
            Set.of(factory.getSWRLObjectPropertyAtom(hasFault, xVar, fVar), factory.getSWRLObjectPropertyAtom(supports, xVar, yVar)),
            Set.of(factory.getSWRLObjectPropertyAtom(hasFault, yVar, fVar))
        ));

        // Rule 7: RF Interface Propagation (Duplexer/Antenna)
        manager.addAxiom(ontology, factory.getSWRLRule(
            Set.of(factory.getSWRLObjectPropertyAtom(hasFault, xVar, fVar), factory.getSWRLObjectPropertyAtom(connectedTo, xVar, yVar)),
            Set.of(factory.getSWRLObjectPropertyAtom(hasFault, yVar, fVar))
        ));

        // Rule 8: Cross-Subsystem Thermal Impact (Overheat Spread)
        manager.addAxiom(ontology, factory.getSWRLRule(
            Set.of(factory.getSWRLObjectPropertyAtom(hasFault, xVar, factory.getSWRLIndividualArgument(fOverheat)), factory.getSWRLObjectPropertyAtom(affectsComponent, factory.getSWRLIndividualArgument(fOverheat), yVar)),
            Set.of(factory.getSWRLObjectPropertyAtom(hasFault, yVar, factory.getSWRLIndividualArgument(fOverheat)))
        ));

        // Rule 9: OBC Decision Logic (Thermal Actions)
        manager.addAxiom(ontology, factory.getSWRLRule(
            Set.of(factory.getSWRLObjectPropertyAtom(hasFault, xVar, factory.getSWRLIndividualArgument(fOverheat))),
            Set.of(factory.getSWRLObjectPropertyAtom(triggersAction, factory.getSWRLIndividualArgument(fOverheat), factory.getSWRLIndividualArgument(safeMode)))
        ));
        manager.addAxiom(ontology, factory.getSWRLRule(
            Set.of(factory.getSWRLObjectPropertyAtom(hasFault, xVar, factory.getSWRLIndividualArgument(fUnderheat))),
            Set.of(factory.getSWRLObjectPropertyAtom(triggersAction, factory.getSWRLIndividualArgument(fUnderheat), factory.getSWRLIndividualArgument(heaterOn)))
        ));
        manager.addAxiom(ontology, factory.getSWRLRule(
            Set.of(factory.getSWRLObjectPropertyAtom(hasFault, xVar, factory.getSWRLIndividualArgument(fSensor))),
            Set.of(factory.getSWRLObjectPropertyAtom(triggersAction, factory.getSWRLIndividualArgument(fSensor), factory.getSWRLIndividualArgument(warningAction)))
        ));
        // === FINAL GLOBAL PROPAGATION RULES END ===

        // === FINAL REASONING FIX RULES START ===
        SWRLIndividualArgument fOverheatArg = factory.getSWRLIndividualArgument(factory.getOWLNamedIndividual(IRI.create(BASE_IRI + "OverheatFault_001")));
        SWRLIndividualArgument fUnderheatArg = factory.getSWRLIndividualArgument(factory.getOWLNamedIndividual(IRI.create(BASE_IRI + "UnderheatFault_001")));
        SWRLIndividualArgument fSensorArg = factory.getSWRLIndividualArgument(factory.getOWLNamedIndividual(IRI.create(BASE_IRI + "SensorFault_001")));

        manager.addAxiom(ontology, factory.getSWRLRule(
            Set.of(
                factory.getSWRLObjectPropertyAtom(hasFault, xVar, fVar),
                factory.getSWRLObjectPropertyAtom(feedsSignalTo, xVar, yVar)
            ),
            Set.of(
                factory.getSWRLObjectPropertyAtom(hasFault, yVar, fVar)
            )
        ));

        manager.addAxiom(ontology, factory.getSWRLRule(
            Set.of(
                factory.getSWRLObjectPropertyAtom(hasFault, xVar, fVar),
                factory.getSWRLObjectPropertyAtom(cools, xVar, yVar)
            ),
            Set.of(
                factory.getSWRLObjectPropertyAtom(hasFault, yVar, fVar)
            )
        ));

        manager.addAxiom(ontology, factory.getSWRLRule(
            Set.of(
                factory.getSWRLObjectPropertyAtom(hasFault, xVar, fVar),
                factory.getSWRLObjectPropertyAtom(heats, xVar, yVar)
            ),
            Set.of(
                factory.getSWRLObjectPropertyAtom(hasFault, yVar, fVar)
            )
        ));

        manager.addAxiom(ontology, factory.getSWRLRule(
            Set.of(
                factory.getSWRLObjectPropertyAtom(hasFault, xVar, factory.getSWRLIndividualArgument(fOverheat)),
                factory.getSWRLObjectPropertyAtom(monitors, xVar, yVar)
            ),
            Set.of(
                factory.getSWRLObjectPropertyAtom(hasFault, yVar, factory.getSWRLIndividualArgument(fOverheat))
            )
        ));

        // OBC decisions
        manager.addAxiom(ontology, factory.getSWRLRule(
            Set.of(factory.getSWRLObjectPropertyAtom(hasFault, xVar, fOverheatArg)),
            Set.of(factory.getSWRLObjectPropertyAtom(triggersAction, fOverheatArg, safeModeArg))
        ));

        manager.addAxiom(ontology, factory.getSWRLRule(
            Set.of(factory.getSWRLObjectPropertyAtom(hasFault, xVar, fUnderheatArg)),
            Set.of(factory.getSWRLObjectPropertyAtom(triggersAction, fUnderheatArg, heaterOnArg))
        ));

        manager.addAxiom(ontology, factory.getSWRLRule(
            Set.of(factory.getSWRLObjectPropertyAtom(hasFault, xVar, fSensorArg)),
            Set.of(factory.getSWRLObjectPropertyAtom(triggersAction, fSensorArg, warningActionArg))
        ));
        // === FINAL REASONING FIX RULES END ===

        File output = new File("satellite_full.owl");
        try (FileOutputStream stream = new FileOutputStream(output)) {
            manager.saveOntology(ontology, new RDFXMLDocumentFormat(), stream);
        }
    }

    private static OWLClass createClass(OWLDataFactory factory, OWLOntology ontology, OWLOntologyManager manager, String name) {
        OWLClass cls = factory.getOWLClass(IRI.create(BASE_IRI + name));
        manager.addAxiom(ontology, factory.getOWLDeclarationAxiom(cls));
        return cls;
    }

    private static OWLObjectProperty createObjectProperty(OWLDataFactory factory, OWLOntology ontology, OWLOntologyManager manager, String name, OWLClass domain, OWLClass range) {
        OWLObjectProperty property = factory.getOWLObjectProperty(IRI.create(BASE_IRI + name));
        manager.addAxiom(ontology, factory.getOWLDeclarationAxiom(property));
        manager.addAxiom(ontology, factory.getOWLObjectPropertyDomainAxiom(property, domain));
        manager.addAxiom(ontology, factory.getOWLObjectPropertyRangeAxiom(property, range));
        return property;
    }

    private static OWLDataProperty createDataProperty(OWLDataFactory factory, OWLOntology ontology, OWLOntologyManager manager, String name, OWLClass domain) {
        return createDataProperty(factory, ontology, manager, name, domain, OWL2Datatype.XSD_DOUBLE.getIRI());
    }

    private static OWLDataProperty createDataProperty(OWLDataFactory factory, OWLOntology ontology, OWLOntologyManager manager, String name, OWLClass domain, IRI datatype) {
        OWLDataProperty property = factory.getOWLDataProperty(IRI.create(BASE_IRI + name));
        manager.addAxiom(ontology, factory.getOWLDeclarationAxiom(property));
        manager.addAxiom(ontology, factory.getOWLDataPropertyDomainAxiom(property, domain));
        manager.addAxiom(ontology, factory.getOWLDataPropertyRangeAxiom(property, factory.getOWLDatatype(datatype)));
        return property;
    }

    private static OWLNamedIndividual createNamedIndividual(OWLDataFactory factory, OWLOntology ontology, OWLOntologyManager manager, String name) {
        OWLNamedIndividual individual = factory.getOWLNamedIndividual(IRI.create(BASE_IRI + name));
        manager.addAxiom(ontology, factory.getOWLDeclarationAxiom(individual));
        return individual;
    }

    private static void addSubClassAxiom(OWLDataFactory factory, OWLOntology ontology, OWLOntologyManager manager, OWLClass subClass, OWLClass superClass) {
        manager.addAxiom(ontology, factory.getOWLSubClassOfAxiom(subClass, superClass));
    }

    private static void addClassAssertion(OWLDataFactory factory, OWLOntology ontology, OWLOntologyManager manager, OWLClass cls, OWLIndividual individual) {
        manager.addAxiom(ontology, factory.getOWLClassAssertionAxiom(cls, individual));
    }

    private static void addObjectPropertyAssertion(OWLDataFactory factory, OWLOntology ontology, OWLOntologyManager manager, OWLObjectProperty property, OWLIndividual subject, OWLIndividual object) {
        manager.addAxiom(ontology, factory.getOWLObjectPropertyAssertionAxiom(property, subject, object));
    }

    private static void addDataPropertyAssertion(OWLDataFactory factory, OWLOntology ontology, OWLOntologyManager manager, OWLDataProperty property, OWLIndividual individual, double value) {
        manager.addAxiom(ontology, factory.getOWLDataPropertyAssertionAxiom(property, individual, factory.getOWLLiteral(value)));
    }

    private static void addDataPropertyAssertion(OWLDataFactory factory, OWLOntology ontology, OWLOntologyManager manager, OWLDataProperty property, OWLIndividual individual, String value) {
        manager.addAxiom(ontology, factory.getOWLDataPropertyAssertionAxiom(property, individual, factory.getOWLLiteral(value)));
    }

    private static void addSWRLRuleIndividual(OWLDataFactory factory, OWLOntology ontology, OWLOntologyManager manager, OWLClass sourceClass, OWLDataProperty dataProperty, IRI builtInIri, double threshold, OWLObjectProperty faultProperty, OWLNamedIndividual faultIndividual) {
        SWRLVariable cVar = factory.getSWRLVariable(IRI.create(BASE_IRI + "c"));
        SWRLVariable vVar = factory.getSWRLVariable(IRI.create(BASE_IRI + "v"));
        SWRLClassAtom classAtom = factory.getSWRLClassAtom(sourceClass, cVar);
        SWRLDataPropertyAtom dataAtom = factory.getSWRLDataPropertyAtom(dataProperty, cVar, vVar);
        OWLLiteral thresholdLiteral = factory.getOWLLiteral(threshold);
        SWRLLiteralArgument thresholdArgument = factory.getSWRLLiteralArgument(thresholdLiteral);
        List<SWRLDArgument> builtInArguments = Arrays.asList(vVar, thresholdArgument);
        SWRLBuiltInAtom builtInAtom = factory.getSWRLBuiltInAtom(builtInIri, builtInArguments);
        SWRLIndividualArgument faultArg = factory.getSWRLIndividualArgument(faultIndividual);
        SWRLObjectPropertyAtom faultPropAtom = factory.getSWRLObjectPropertyAtom(faultProperty, cVar, faultArg);
        Set<SWRLAtom> body = new HashSet<>(Arrays.asList(classAtom, dataAtom, builtInAtom));
        Set<SWRLAtom> head = new HashSet<>(Arrays.asList(faultPropAtom));
        SWRLRule rule = factory.getSWRLRule(body, head);
        manager.addAxiom(ontology, rule);
    }
}



