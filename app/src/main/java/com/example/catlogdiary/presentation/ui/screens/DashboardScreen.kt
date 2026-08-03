package com.example.catlogdiary.presentation.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.catlogdiary.presentation.viewmodel.AppViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(viewModel: AppViewModel = hiltViewModel()) {
    val cats by viewModel.cats.collectAsState()
    val weights by viewModel.weights.collectAsState(initial = emptyList())
    val selectedCatId by viewModel.selectedCatId.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        TopAppBar(
            title = { Text("CatLog Diary Core", fontWeight = FontWeight.Bold) },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.background,
                titleContentColor = MaterialTheme.colorScheme.primary
            )
        )

        val activeCat = cats.find { it.id == selectedCatId }
        if (activeCat != null) {
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Active Profile: ${activeCat.name}", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                    Text("Breed: ${activeCat.breed} | Age: ${activeCat.age} years", fontSize = 14.sp)
                }
            }
        }

        Text("Feline Weight Spline Curve (Canvas)", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.height(8.dp))

        // Custom Spline Chart Drawing on Canvas
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(MaterialTheme.colorScheme.surface, shape = MaterialTheme.shapes.medium)
                .padding(12.dp)
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val width = size.width
                val height = size.height
                
                // Draw target range band
                drawRect(
                    color = Color(0xFFFFECE8),
                    topLeft = Offset(0f, height * 0.3f),
                    size = androidx.compose.ui.geometry.Size(width, height * 0.4f)
                )

                if (weights.size >= 2) {
                    val path = Path()
                    val points = weights.take(5).reversed().mapIndexed { index, weightLog ->
                        val x = index * (width / 4f)
                        // Normalize weight 4.0 to 5.0 kg on screen height
                        val norm = ((weightLog.weightKg - 4.0) / 1.0).coerceIn(0.0, 1.0)
                        val y = height - (norm.toFloat() * height)
                        Offset(x, y)
                    }

                    path.moveTo(points[0].x, points[0].y)
                    for (i in 1 until points.size) {
                        val prev = points[i - 1]
                        val curr = points[i]
                        // Simple cubic spline control points
                        val cp1 = Offset(prev.x + (curr.x - prev.x) / 2f, prev.y)
                        val cp2 = Offset(prev.x + (curr.x - prev.x) / 2f, curr.y)
                        path.cubicTo(cp1.x, cp1.y, cp2.x, cp2.y, curr.x, curr.y)
                    }

                    drawPath(
                        path = path,
                        color = Color(0xFFE76F51),
                        style = Stroke(width = 4f)
                    )

                    points.forEach { pt ->
                        drawCircle(color = Color(0xFFE9C46A), radius = 6f, center = pt)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text("Weight logs history list:", fontSize = 14.sp, fontWeight = FontWeight.SemiBold)

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(weights) { w ->
                Row(
                    modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.surface).padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Logged Weight", fontWeight = FontWeight.Medium)
                    Text("${w.weightKg} kg", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
