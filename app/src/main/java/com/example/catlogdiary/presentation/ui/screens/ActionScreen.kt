package com.example.catlogdiary.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.catlogdiary.presentation.viewmodel.AppViewModel

@Composable
fun ActionScreen(viewModel: AppViewModel = hiltViewModel()) {
    var weightText by remember { mutableStateOf("4.9") }
    var selectedLitterType by remember { mutableStateOf("Urine") }
    var bloodPresence by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text("Add Health & Litter Log", style = MaterialTheme.typography.headlineMedium, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(24.dp))

            // Weight Entry Block
            Card(modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Add Weight Curve Measure", fontWeight = FontWeight.SemiBold)
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        OutlinedTextField(
                            value = weightText,
                            onValueChange = { weightText = it },
                            label = { Text("Weight (kg)") },
                            modifier = Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Button(onClick = {
                            weightText.toDoubleOrNull()?.let { viewModel.addWeight(it) }
                        }) {
                            Text("Log Weight")
                        }
                    }
                }
            }

            // Litter Entry Block
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Add Litter Box Activity Event", fontWeight = FontWeight.SemiBold)
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Button(
                            onClick = { selectedLitterType = "Urine" },
                            colors = ButtonDefaults.buttonColors(containerColor = if (selectedLitterType == "Urine") MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant)
                        ) {
                            Text("Urine", color = MaterialTheme.colorScheme.onSurface)
                        }
                        Button(
                            onClick = { selectedLitterType = "Stool" },
                            colors = ButtonDefaults.buttonColors(containerColor = if (selectedLitterType == "Stool") MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant)
                        ) {
                            Text("Stool", color = MaterialTheme.colorScheme.onSurface)
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(checked = bloodPresence, onCheckedChange = { bloodPresence = it })
                        Text("Blood Presence / Straining detected")
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = { viewModel.addLitter(selectedLitterType, bloodPresence) },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Save Litter Entry")
                    }
                }
            }
        }
    }
}
