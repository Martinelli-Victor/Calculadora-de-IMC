package com.example.calculadoraimc

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SeekBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*
import java.math.RoundingMode
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    lateinit var texto1: TextView
    lateinit var seekBar1: SeekBar

    lateinit var texto2: TextView
    lateinit var seekBar2: SeekBar

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        texto1 = findViewById(R.id.txvPeso)
        seekBar1 = findViewById(R.id.skbPeso)

        seekBar1.min = 0
        seekBar1.max = 200


        texto2 = findViewById(R.id.txvAltura)
        seekBar2 = findViewById(R.id.skbAltura)

        seekBar2.min = 100
        seekBar2.max = 250


        imvHomem.isVisible = false
        imvMulher.isVisible = false


        txvIndice.text = ""
        txvClassif.text = ""
        txvI.text = ""
        txvC.text = ""


        seekBar1.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {

                texto1.text = "" + p1 + "Kg"
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                txvIndice.text = ""
                txvClassif.text = ""
                txvI.text = ""
                txvC.text = ""
                imvHomem.isVisible = false
                imvMulher.isVisible = false

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }

        })

        seekBar2.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p3: Int, p2: Boolean) {

                texto2.text = "" + (p3 / 100.toFloat()) + "m"
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                txvIndice.text = ""
                txvClassif.text = ""
                txvI.text = ""
                txvC.text = ""
                imvHomem.isVisible = false
                imvMulher.isVisible = false

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

        })


        var listaSexo = arrayListOf<String>()

        listaSexo.addAll(listOf("Feminino", "Masculino"))


        val listaAdaptada = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, listaSexo)

        spnSexo.adapter = listaAdaptada


        btnResultado.setOnClickListener() {

            txvIndice.text = "Índice"
            txvClassif.text = "Classificação"

            var peso = seekBar1.progress.toInt()
            var altura = seekBar2.progress.toFloat()
            var indice = (peso / (altura * altura)) * 10000

            val df = DecimalFormat ("#.##")
            df.roundingMode = RoundingMode.CEILING

            txvI.text = (df.format(indice)).toString()


            if (indice < 18.5) {
                txvC.text = "Abaixo do peso"

            } else if (indice >= 18.5 && indice <= 24.9) {
                txvC.text = "Peso normal"

            } else if (indice >= 25 && indice <= 29.9){
                txvC.text = "Sobrepeso"

            } else if (indice >= 30 && indice <= 34.9) {
                txvC.text = "Obesidade grau 1"

            } else if (indice >= 35 && indice <= 39.9) {
                txvC.text = "Obesidade grau 2"

            } else if (indice > 40) {
                txvC.text = "Obesidade grau 3"

            }

            val sexoSelecionado = spnSexo.selectedItem as String

            if (sexoSelecionado == "Masculino"){

                imvHomem.isVisible = true
                imvMulher.isVisible = false

            } else {

                imvMulher.isVisible = true
                imvHomem.isVisible = false
            }


            }

        btnTabela.setOnClickListener(){

            val tabelaImc = Intent(this,TabelaIMC::class.java)
            startActivity(tabelaImc)
        }


        }

    }

