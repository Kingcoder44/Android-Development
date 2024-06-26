package com.example.shoppinglist.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class shoppingItem(val id: Int,
                        var name: String,
                        var quantity     : Int,
                        var isEditable: Boolean=false)

@Composable
fun ShoppingListApp()
{

    var sItems by remember{ mutableStateOf(listOf<shoppingItem>()) }
    var showDialog by remember{mutableStateOf(false)}
    var itemName by remember { mutableStateOf("") }
    var itemQuant by remember { mutableStateOf("") }
    Column(
        modifier= Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { showDialog=true }, modifier = Modifier.align(Alignment.CenterHorizontally))
        {
            Text(text = "Add Items")
        }
        LazyColumn(
            modifier= Modifier
                .fillMaxSize()

        ) {
            items(sItems){}
        }

    }
    if(showDialog)
    {
        AlertDialog(
            onDismissRequest = { showDialog=false },
            confirmButton = { Row(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween) {
                Button(onClick =
                {
                    if(itemName.isNotBlank())
                    {
                        val newItem = shoppingItem(
                        id=sItems.size+1,
                        name=itemName,
                        quantity = itemQuant.toInt()
                        )
                    sItems=sItems+newItem
                    itemName=""
                    showDialog=false
                    }
                })
                {
                    Text(text = "Add")
                }
                Button(onClick = {showDialog=false }) {
                    Text(text = "Cancel")
                }
            }
                            },
            title = {Text("Add Shopping Items")},
            text = {
                Column {
                    OutlinedTextField(value = itemName,
                        onValueChange = {itemName=it},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        singleLine=true)

                    OutlinedTextField(value = itemQuant,
                        onValueChange = {itemQuant=it},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        singleLine=true)

                }
            }
        )
    }
}