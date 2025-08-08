# Random IT Scenario Generator (Java)

## Overview
This is a simple Java REST API that generates random IT-related scenarios based on user inputs.  
You provide:
- **Technology** (e.g., Cloud Computing, Cybersecurity, DevOps)
- **Role** (e.g., System Administrator, Software Engineer, Security Analyst)
- **Environment** (e.g., Enterprise Network, Cloud Infrastructure, On-Prem Data Center)

The API will return:
- Your original inputs
- A random challenge, incident, and troubleshooting step from a predefined dataset

This project was built for a **Junior Java Developer** assignment following a **Test Driven Development** mindset.

---

## How It Works
1. You send a POST request with JSON body containing:
```json
{
  "technology": "Java",
  "role": "Backend Developer",
  "environment": "On-Premise"
}
```
2. The API randomly picks:
   - A challenge
   - An incident
   - A troubleshooting step
3. It returns them in structured JSON format.

---

## How to Run

### 1. Compile
```bash
javac Main.java ScenarioGenerator.java
```

### 2. Run
```bash
java Main
```
This starts the server on **http://localhost:3000**.

---

## Example Request (curl)
```bash
curl -X POST http://localhost:3000/generate   -H "Content-Type: application/json"   -d '{"technology":"Java", "role":"Backend Developer", "environment":"On-Premise"}'
```

---

## Example Response
```json
{
  "inputs": {
    "technology": "Java",
    "role": "Backend Developer",
    "environment": "On-Premise"
  },
  "scenario": {
    "challenge": "Integration with legacy systems",
    "incident": "System outage during peak hours",
    "troubleshooting": "Restart affected services and monitor logs"
  }
}
```

---

## Notes
- No frameworks used (pure Java).
- Dataset is predefined inside `ScenarioGenerator.java`.
- Response changes on each request because of random selection.
