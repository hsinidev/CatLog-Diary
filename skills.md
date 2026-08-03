# CatLog Diary — Skills and Architecture Guide

## Category: Pet Health / Lifestyle

## Overview
A health tracking app optimized exclusively for cats to log litter-box frequency, precise weight curves, food intake, and medication intervals. Built for devoted cat owners.

## Data Architecture
```
data/
  database/
    AppDatabase.kt
    [Domain]Dao.kt
    [Domain]Entity.kt
  repository/
    [Domain]RepositoryImpl.kt
domain/
  model/        — Domain data classes
  repository/   — Repository interfaces
  usecase/      — Business logic use cases
presentation/
  ui/screens/   — Jetpack Compose screens
  viewmodel/    — StateFlow-based ViewModels
```

## Key Technical Skills
- **Room Database**: Define entities with proper @Entity, @PrimaryKey, @ColumnInfo annotations
- **Hilt DI**: @HiltViewModel, @Inject, @Singleton, @Provides in AppModule
- **Jetpack Compose**: LazyColumn, AnimatedVisibility, Canvas, remember, collectAsState
- **WorkManager**: PeriodicWorkRequest for daily reminders, OneTimeWorkRequest for processing
- **StateFlow**: Single UiState sealed class pattern for all screen states
- **Navigation**: NavHost with type-safe arguments, nested navigation for bottom nav

## Notification Strategy
```kotlin
val request = PeriodicWorkRequestBuilder<ReminderWorker>(1, TimeUnit.DAYS)
    .setInitialDelay(nextReminderDelay, TimeUnit.MILLISECONDS)
    .build()
WorkManager.getInstance(context).enqueueUniquePeriodicWork(
    "daily_reminder", ExistingPeriodicWorkPolicy.UPDATE, request
)
```

## Google Play Billing Integration
```kotlin
// Query subscription status
val params = QueryPurchasesParams.newBuilder()
    .setProductType(BillingClient.ProductType.SUBS).build()
val result = billingClient.queryPurchasesAsync(params)
val isPro = result.purchasesList.any { it.purchaseState == Purchase.PurchaseState.PURCHASED }
```

## Play Store Checklist
- [ ] App icon 512x512 PNG
- [ ] Feature graphic 1024x500 PNG
- [ ] 4+ screenshots showing key screens
- [ ] Short description (80 chars max)
- [ ] Full description (4000 chars max) with keywords
- [ ] Privacy Policy URL
- [ ] Content rating questionnaire completed
- [ ] Target SDK 34+
- [ ] Signed release APK or AAB
