# Document Architecture Notification — Semaine 1 (ROBOCARE)

> **Destinataire :** Encadrant  
> **Équipe :** Binôme stagiaires (Mois 2 — Semaine 1)  
> **Objectif :** Document court, synthétique et validable servant de base pour les semaines suivantes.

---

## 1. Contexte

**ROBOCARE** propose des services d'agriculture connectée (agriculture de précision).  
Le système de notification doit alerter rapidement les utilisateurs (agriculteurs, agronomes) sur des événements critiques : humidité basse, pluie imminente, détection de maladie/ravageurs, rapports périodiques, etc.

**Multi-canal dès le MVP :** Email, SMS, WhatsApp, Push mobile.  
L'équilibre entre urgence, coût et préférence utilisateur guide chaque notification.

---

## 2. Objectifs Semaine 1

- Lister les principaux cas d'usage et les formaliser dans un tableau
- Définir les canaux supportés dès le départ
- Identifier les principaux triggers/events déclencheurs
- Lister les contraintes majeures (techno, volumétrie, sécurité)

---

## 3. Cas d'usage principaux

| # | Cas d'usage | Déclencheur | Canal(s) | Urgence |
|---|---|---|---|---|
| 1 | Alerte humidité basse | Capteur IoT (seuil dépassé) | SMS, WhatsApp, Push, Email | 🔴 Critique |
| 2 | Alerte météo (pluie, orage) | API météo externe | SMS, WhatsApp, Push, Email | 🟠 Haute |
| 3 | Détection maladie / ravageur | Diagnostic IA embarqué | SMS, WhatsApp, Push, Email | 🔴 Critique |
| 4 | Alerte irrigation manquée | Planificateur + IoT | SMS, Push | 🟠 Haute |
| 5 | Rapport hebdomadaire parcelle | Tâche planifiée (cron)         | Email, WhatsApp, Push | 🟡 Moyenne |
| 6 | Confirmation d'action utilisateur | Action manuelle / dashboard | Email, Push | 🟢 Basse |
| 7 | Notification de mise à jour système | Action admin | Email | 🟢 Basse |

---

## 4. Triggers / Events déclencheurs

| Source | Type | Exemples |
|---|---|---|
| Capteurs IoT | Temps réel | Humidité, température, pluie |
| Analyses IA | Asynchrone | Diagnostic maladie, recommandation |
| API météo externe | Planifié / webhook | Prévisions pluie, alerte orage |
| Tâche planifiée (cron) | Périodique | Rapport hebdomadaire, bilan parcelle |
| Action manuelle | Événement utilisateur / admin | Confirmation action dashboard |

---

## 5. Canaux supportés

| Canal | Statut MVP | Fournisseur envisagé |
|---|---|---|
| Email | ✅ Oui | SMTP / JavaMailSender (Spring) |
| SMS | ✅ Oui | Opérateur local ou API (ex. Twilio) |
| WhatsApp | ✅ Oui | WhatsApp Business API |
| Push mobile | ✅ Oui | Firebase Cloud Messaging (FCM) |

---

## 6. Contraintes majeures

### Volumétrie
- De **1 000 à 10 000 notifications/jour**, avec des pics selon la saison agricole.
- Le système doit être conçu pour absorber ces pics sans dégradation.

### Coût
- SMS et WhatsApp ont un coût unitaire élevé → envoi conditionné à l'urgence et aux préférences utilisateur.
- Favoriser Email/Push pour les notifications de faible urgence.

### Préférences utilisateur
- Respect des **heures de silence** configurées par l'utilisateur.
- Canal préféré configurable par profil utilisateur.

### Sécurité
- API sécurisée (authentification JWT ou clé API).
- Protection des données personnelles (numéros de téléphone, emails) conformément au **RGPD**.

### Traçabilité / Audit
- Conservation des **logs d'envoi** (statut, horodatage, canal, erreur éventuelle).
- Durée de conservation définie selon politique RGPD du projet.

### Résilience
- Mécanisme de **retry** en cas d'échec d'envoi (ex. 3 tentatives avec backoff).
- Statuts de notification clairs : `PENDING` → `PROCESSING` → `SENT` / `FAILED`.

---

## 7. Prochaines étapes (Semaines 2–4)

| Semaine | Focus | Livrables |
|---|---|---|
| Semaine 2 | APIs + Base de données | Swagger, Schéma DB |
| Semaine 3 | Templates + Dashboard | Templates dynamiques, Wireframes |
| Semaine 4 | Tests + Validation | Stratégie de test, validation globale |

---

_Ce document sert de base de validation pour l'encadrant avant d'entamer la conception technique détaillée (Semaines 2–4)._
