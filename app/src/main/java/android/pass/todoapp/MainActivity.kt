package android.pass.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.pass.todoapp.databinding.ActivityMainBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var todoAdapter: TodoAdapter
    lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerView = binding.todoitemsRecyclerView

        todoAdapter = TodoAdapter(mutableListOf())

        recyclerView.adapter = todoAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        binding.BtnToDO.setOnClickListener{
            val todoTitle = binding.todoEdittext.text.toString()
            if (todoTitle.isNotEmpty())
            {
                val todo = Todo(todoTitle)
                todoAdapter.addTodo(todo)
binding.todoEdittext.text.clear()
            }
        }


        binding.BtnDeleteTodo.setOnClickListener{
            todoAdapter.deleteTodo()
        }






    }
}