package com.leonardo.jokenpo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    //Imagem do Computador
    private lateinit var imgComputer: ImageView

    //Imagem do Usuário
    private lateinit var imgUser: ImageView

    //Resultado final
    private lateinit var txtResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imgComputer = findViewById(R.id.imgComputador)
        imgUser = findViewById(R.id.imgUsuario)
        txtResult = findViewById(R.id.txtResultado)

        //Em breve: Dividir as funções em outros arquivos; Criar um SQLite para salvar o histórico de jogos.

        //Opções do usuário
        val btnPedra = findViewById<Button>(R.id.btnPedra)
        val btnPapel = findViewById<Button>(R.id.btnPapel)
        val btnTesoura = findViewById<Button>(R.id.btnTesoura)

        //Opção Pedra
        btnPedra.setOnClickListener {
            userChoice(R.drawable.pedra)
            val computerChoice = computerChoice()

            txtResult.text = jokenPo(R.drawable.pedra, computerChoice)
            setResultColor(txtResult.text.toString())
        }

        //Opção Papel
        btnPapel.setOnClickListener {
            userChoice(R.drawable.papel)
            val computerChoice = computerChoice()

            txtResult.text = jokenPo(R.drawable.papel, computerChoice)
            setResultColor(txtResult.text.toString())
        }

        //Opção Tesoura
        btnTesoura.setOnClickListener {
            userChoice(R.drawable.tesoura)
            val computerChoice = computerChoice()

            txtResult.text = jokenPo(R.drawable.tesoura, computerChoice)
            setResultColor(txtResult.text.toString())
        }

        //Mostrar histórico
        /*val btnHistory = findViewById<Button>(R.id.btnHistorico)

        btnHistory.setOnClickListener {
            val dbHelper = MatchHistory(this)
            val cursor = dbHelper.getAllMatches()

            cursor.close()
            dbHelper.close()
        }*/
    }

    private fun userChoice(choice: Int) {
        //Imagem da opção do usuário
        imgUser.setImageResource(choice)
    }

    private fun computerChoice(): Int {
        //Opções + escolha aleatória do computador
        val options = listOf(R.drawable.pedra, R.drawable.papel, R.drawable.tesoura)
        val choice = options.random()

        //Imagem da opção do computador
        imgComputer.setImageResource(choice)
        return choice
    }

    private fun jokenPo(userChoice: Int, computerChoice: Int): String {

        //Mostrar histórico
        /*val dbHelper = MatchHistory(this)
        val result = dbHelper.insertMatch(userChoice.toString(), computerChoice.toString(), txtResult.toString())
        Log.d("MatchHistory", "Resultado: $result")
        dbHelper.close()*/

        //Resultado final
        return when (userChoice) {
            R.drawable.pedra ->
                when (computerChoice) {
                    R.drawable.pedra -> "Empate!"
                    R.drawable.papel -> "Você perdeu!"
                    R.drawable.tesoura -> "Você ganhou!"
                    else -> "Erro"
                }

            R.drawable.papel ->
                when (computerChoice) {
                    R.drawable.pedra -> "Você ganhou!"
                    R.drawable.papel -> "Empate!"
                    R.drawable.tesoura -> "Você perdeu!"
                    else -> "Erro"
                }

            R.drawable.tesoura ->
                when (computerChoice) {
                    R.drawable.pedra -> "Você perdeu!"
                    R.drawable.papel -> "Você ganhou!"
                    R.drawable.tesoura -> "Empate!"
                    else -> "Erro"
                    }

            else -> "Erro"
        }
    }

    //Altera a cor do texto mediante o resultado final
    private fun setResultColor(result: String) {
        when {
            result.contains("Você ganhou!") -> txtResult.setTextColor(getColor(R.color.green))
            result.contains("Você perdeu!") -> txtResult.setTextColor(getColor(R.color.red))
            result.contains("Empate!") -> txtResult.setTextColor(getColor(R.color.yellow))
            else -> txtResult.setTextColor(getColor(R.color.white))
        }
    }
}