package com.example.mviprojectex1.ui

import android.widget.ProgressBar
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mviprojectex1.model.MainAppUIState
import com.example.mviprojectex1.model.Result
import com.example.mviprojectex1.ui.theme.MVIProjectEx1Theme

@Composable
fun MainAppScreen() {
    var viewModel: MainViewModel = viewModel(factory = MainViewModel.Factory)
    val appState by viewModel.mutableStateFlow.collectAsState()
    when (appState) {
        MainAppUIState.Error -> TODO()
        MainAppUIState.Loading -> ShowLoading()
        MainAppUIState.Nothing -> {}
        is MainAppUIState.Success -> {
            val data = (appState as MainAppUIState.Success).list
            ShowList(result = data?.get(0)!!)
        }
    }

}


@Composable
@Preview(showSystemUi = true)
fun MainAppScreenPreview() {
    MainAppScreen()
}

@Composable
fun ShowLoading() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(100.dp)
        )
    }
}

@Composable
fun ShowList(result: Result) {
    var textValue by remember { mutableStateOf("") }
    textValue = result._id

    MVIProjectEx1Theme {
        Surface(
            Modifier.fillMaxSize(),
        ) {

            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally

            )
            {
                val context = LocalContext.current
                TextField(value = textValue, onValueChange = { textValue = it },
                    label = { "Hello" }
                )
                Spacer(modifier = Modifier.height(14.dp))

                Button(
                    onClick = {
                        Toast.makeText(context, "Hi", Toast.LENGTH_LONG).show()
                    },
                    modifier = Modifier
                        .width(200.dp)
                        .height(50.dp),
                ) {
                    Text(text = "Goto Home")
                }
                Text(text = "Hi")

            }
        }
    }
}