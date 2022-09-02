package android.pass.todoapp

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.text.style.StrikethroughSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class TodoAdapter(private val todolist : MutableList<Todo> ) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>()
{



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
    return TodoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_to_do,parent,false))

    }

    fun addTodo(todo: Todo) {

      todolist.add(todo)
     notifyItemInserted(todolist.size-1)
    }

    fun deleteTodo() {

        todolist.removeAll {
            todo -> todo.ischecked
        }
notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(todoTitle : TextView , isChecked : Boolean)
    {
        if(isChecked)
        {
            todoTitle.paintFlags = todoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        }
        else{
            todoTitle.paintFlags = todoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }

    }

    override fun onBindViewHolder(holder: TodoAdapter.TodoViewHolder, position: Int) {
        val currTodo = todolist[position]
     holder.todoTitle.text = currTodo.todoTitle.toString()
        holder.isChecked.isChecked = currTodo.ischecked
        toggleStrikeThrough(holder.todoTitle , currTodo.ischecked )
        holder.isChecked.setOnCheckedChangeListener { _, isChecked ->
            toggleStrikeThrough(holder.todoTitle ,isChecked )
            currTodo.ischecked = !currTodo.ischecked
        }


    }

    override fun getItemCount(): Int {
       return todolist.size
    }

    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val todoTitle : TextView = itemView.findViewById(R.id.itemtitletodo)
        val isChecked : CheckBox = itemView.findViewById(R.id.itemcheck)
    }

}