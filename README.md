# CatLog Diary - Advanced Feline Health Tracker & Care Scheduler

> Log litter habits, monitor weight curves, and secure medication intervals.

**Pet Health & Lifestyle / Analytics** built with Kotlin and modern Android development standards.

---

## 📖 How It Works

This application is built following **Clean Architecture**, **MVVM / MVI pattern**, and **Offline-First** principles.

### Architecture & System Modules
- **`:app` Module**: Application initialization, Hilt module graphs, dynamic provider bindings, and safe NavHost controllers.
- **`:core_ui` Module**: Feline-focused design tokens (Warm Salmon Pink, Soft Calico Cream, Cool Mint Green), custom path charts, and spring animation interpolators.
- **`:feature_profile` Module**: Profile creator views, breed dictionaries, avatar croppers, and local storage picture repositories.
- **`:feature_tracker` Module**: Litter-box logging sheets, food/water calculators, symptom photo binders, and anomaly logic checkers.
- **`:feature_scheduler` Module**: AlarmManager receivers, recurring interval calculators, dosage logs, and completion checkers.
- **`:feature_analytics` Module**: Custom Canvas charts (feline weight splines, food segment rings), vet-ready PDF exporter engines, and data CSV builders.
- **`:feature_pro` Module**: Google Play Billing 6.x wrappers, dynamic tier comparison widgets, premium widget builders, and paywall screens.

### Required Android Permissions
- `android.permission.READ_MEDIA_IMAGES (Required for choosing profile pictures and logging symptom photos on SDK 33+)`
- `android.permission.POST_NOTIFICATIONS (Crucial to fire medication and health reminder alerts on SDK 33+)`
- `android.permission.SCHEDULE_EXACT_ALARM (Required for scheduled critical insulin alarms on Android 13+)`
- `android.permission.USE_EXACT_ALARM (Required for Android 14+ policies on medication-specific scheduling)`
- `android.permission.INTERNET (For processing Google Play Billing, loading AdMob ads, and syncing databases)`

---

## 📱 How to Use

### 1. Multi Cat Profile Engine
Manage individual feline health baselines securely in isolated data containers.
- Dynamic Cat Registration: Support logging breed (loaded from local JSON), birthdate, gender, microchip ID, sterilization status, and activity baseline.
- Profile Avatar Processor: Build an interactive image selection and circular cropper tool, saving optimized profile thumbnails directly to local app storage.

### 2. Litter Box Tracker Engine
Log, categorize, and flag litter-box patterns to identify early signs of FLUTD or other urinary anomalies.
- Granular Event Logging: Capture urine/stool markers with consistency scales (Bristol Stool Chart adapted for cats), relative volume sliders, and dynamic anomaly indicators (blood presence, excessive straining).
- Automated Anomaly Flags: Write domain-level rules that analyze temporal frequency anomalies (e.g., logging urine events >4 times in 12 hours) and fire highly visible, non-alarmist health suggestions to consult a veterinarian.

### 3. Precision Weight Analytics Curve
Custom-rendered, interactive spline graphs tracking subtle weight deviations crucial for feline disease detection.

### 4. Medication And Insulin Scheduler
An iron-clad, exact alarm manager tracking life-critical feline medications.
- Exact Alarm Dispatcher: Inject recurring exact alarms utilizing Android's AlarmManager.setExactAndAllowWhileIdle to guarantee critical insulin or thyroid notifications fire on time.
- Compliance Checklist: Create daily calendar sheets with touch-interactive checkboxes that log doses administered, logging metadata (dosage, administrator, and timestamp) in Room.

### 5. Admob Monetization Layer



---

## 🚀 Key Features

- **Multi Cat Profile Engine**: Manage individual feline health baselines securely in isolated data containers.
  - Dynamic Cat Registration: Support logging breed (loaded from local JSON), birthdate, gender, microchip ID, sterilization status, and activity baseline.
  - Profile Avatar Processor: Build an interactive image selection and circular cropper tool, saving optimized profile thumbnails directly to local app storage.
- **Litter Box Tracker Engine**: Log, categorize, and flag litter-box patterns to identify early signs of FLUTD or other urinary anomalies.
  - Granular Event Logging: Capture urine/stool markers with consistency scales (Bristol Stool Chart adapted for cats), relative volume sliders, and dynamic anomaly indicators (blood presence, excessive straining).
  - Automated Anomaly Flags: Write domain-level rules that analyze temporal frequency anomalies (e.g., logging urine events >4 times in 12 hours) and fire highly visible, non-alarmist health suggestions to consult a veterinarian.
- **Precision Weight Analytics Curve**: Custom-rendered, interactive spline graphs tracking subtle weight deviations crucial for feline disease detection.
- **Medication And Insulin Scheduler**: An iron-clad, exact alarm manager tracking life-critical feline medications.
  - Exact Alarm Dispatcher: Inject recurring exact alarms utilizing Android's AlarmManager.setExactAndAllowWhileIdle to guarantee critical insulin or thyroid notifications fire on time.
  - Compliance Checklist: Create daily calendar sheets with touch-interactive checkboxes that log doses administered, logging metadata (dosage, administrator, and timestamp) in Room.
- **Admob Monetization Layer**: 

---

## 🛠️ Tech Stack & Architecture

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose (Material Design 3)
- **Architecture**: Clean Architecture + MVVM / MVI
- **Local Storage**: Room Database & DataStore
- **Async Operations**: Kotlin Coroutines & StateFlow
- **Build System**: Gradle Kotlin DSL
- **Min SDK**: 26 | **Target SDK**: 34

---

## 💻 Getting Started

### Prerequisites
- Android Studio Ladybug (2024.2.1+) or newer
- JDK 17+
- Android SDK 34+

### Building & Running
1. Clone the repository:
   ```bash
   git clone https://github.com/hsinidev/CatLog-Diary.git
   cd CatLog-Diary
   ```
2. Open the project in Android Studio.
3. Sync Gradle dependencies and run on an Android device or emulator.

---

## 📬 Contact & Support

Created and maintained by **Hsini**.

- **Website**: [hsini.dev](https://hsini.dev)
- **Email**: [contact@hsini.dev](mailto:contact@hsini.dev)
- **GitHub**: [@hsinidev](https://github.com/hsinidev)

---

© 2026 [hsini.dev](https://hsini.dev). All rights reserved.
