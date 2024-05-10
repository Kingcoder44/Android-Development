package com.example.unitconvertor

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconvertor.ui.theme.UnitConvertorTheme
import kotlin.math.roundToInt
import kotlin.time.times

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
    }
}
@Composable
fun UnitCon(){
    var inputValue by remember{ mutableStateOf("") }
    var outputValue by remember { mutableStateOf("")}
    var inputUnit by remember { mutableStateOf("Meters") }
    var outputUnit by remember { mutableStateOf("Meters") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false)}
    var conversionFactor = remember { mutableStateOf(1.00) }
    var oconversionFactor = remember { mutableStateOf(1.00) }// value of this will be changed in each of the dropdown item

    //function to convert the input values
    fun conver()
    {
        //?:-elvis operator which takes value 0.0 if second part is true i.e. input value is not valid and changes it to 0.0
        var inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        var result = (inputValueDouble * conversionFactor.value*100.0 / oconversionFactor.value).roundToInt() / 100.0
        outputValue = result.toString()
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var context = LocalContext.current
        //in this elements are stacked on top of each other
        Text("Unit Convertor", modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputValue, onValueChange = {
          inputValue=it
         conver()//it is an anonymous function in which what happens on entering the text in field
         },label= {Text(text = "Enter text")})
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            //in this elementsa are stacked side by side
            //input Box
           Box{
               Button(onClick = {
                   iExpanded=true
               }) {
                   Text(inputUnit)
                   Icon(Icons.Default.ArrowDropDown, contentDescription = "")
               }
               DropdownMenu(expanded = iExpanded , onDismissRequest = { /*TODO*/ iExpanded=false}) {
                   DropdownMenuItem(text = { Text(text = "Centimeters")}, onClick = { /*TODO*/
                   iExpanded=false
                   inputUnit="Centimeters"
                       conversionFactor.value=0.01
                       conver()
                   })
                   DropdownMenuItem(text = {Text(text = "Meters")}, onClick = { /*TODO*/iExpanded=false
                       inputUnit="Meters"
                       conversionFactor.value=1.0
                       conver()

                        })
                   DropdownMenuItem(text = {Text(text = "Feet")}, onClick = { /*TODO*/ iExpanded=false
                       inputUnit="Feet"
                       conversionFactor.value=0.3280840
                       conver()
                   })
                   DropdownMenuItem(text = {Text(text = "Millimeters")}, onClick = { /*TODO*/ iExpanded=false
                       inputUnit="Millimeters"
                       conversionFactor.value=0.001
                       conver()
                       })
               }

           }
            Spacer(modifier = Modifier.width(16.dp))
            //output box
            Box{
                Button(onClick = {oExpanded=true}) {
                    Text(outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "")
                }
                DropdownMenu(expanded = oExpanded , onDismissRequest = { /*TODO*/oExpanded=false }) {
                    DropdownMenuItem(text = { Text(text = "Centimeters")}, onClick = { /*TODO*/
                        oExpanded=false
                        outputUnit="Centimeters"
                       oconversionFactor.value=0.01
                       conver() })
                    DropdownMenuItem(text = {Text(text = "Meters")}, onClick = { /*TODO*/
                        oExpanded=false
                        outputUnit="Meters"
                        oconversionFactor.value=1.00
                        conver()})
                    DropdownMenuItem(text = {Text(text = "Feet")}, onClick = { /*TODO*/
                        oExpanded=false
                        outputUnit="Feet"
                        oconversionFactor.value=0.328084
                        conver()})
                    DropdownMenuItem(text = {Text(text = "Millimeters")}, onClick = { /*TODO*/
                        oExpanded=false
                        outputUnit="Millimeters"
                        oconversionFactor.value=0.001
                        conver()})
                }

            }

        }
        Spacer(modifier = Modifier.height(8.dp))
        // Result Text
        Text("Result: $outputValue $outputUnit",
            //to change font style
    style=MaterialTheme.typography.headlineMedium)

    }
}
@Preview(showBackground = true)
@Composable()
fun UnitConPreview()
{
    UnitCon()

}