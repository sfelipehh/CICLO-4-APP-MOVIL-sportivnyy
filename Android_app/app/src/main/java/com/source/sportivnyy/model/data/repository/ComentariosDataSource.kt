package com.source.sportivnyy.model.data.repository

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.source.sportivnyy.model.data.Comentario

class ComentariosDataSource(resources: Resources) {
    private val initialCometariosList = comentariosList(resources)
    private val comentariosLiveData = MutableLiveData(initialCometariosList)

    fun addCommentario(comentario: Comentario){
        val currentList = comentariosLiveData.value
        if(currentList==null){
            comentariosLiveData.postValue(listOf(comentario))
        }else{
            val updatedList = currentList.toMutableList()
            updatedList.add(0, comentario)
            comentariosLiveData.postValue(updatedList)
        }
    }

    fun removeComentario(comentario: Comentario){
        val currentList = comentariosLiveData.value
        if(currentList!=null){
            val updatedList = currentList.toMutableList()
            updatedList.remove(comentario)
            comentariosLiveData.postValue(updatedList)
        }
    }

    fun getComentarioForID(id:Long):Comentario?{
        comentariosLiveData.value?.let{
                cometario -> return cometario.firstOrNull{it.id ==id}
        }
        return null
    }

    fun getCometariosList():LiveData<List<Comentario>>{
        return comentariosLiveData
    }

    fun getRandomComentarioImageAsset():Int?{
        val randomNumber = (initialCometariosList.indices).random()
        return initialCometariosList[randomNumber].author_photo
    }

    companion object{
        private var INSTANCE:ComentariosDataSource?=null

        fun getComentariosDataSource(resources: Resources):ComentariosDataSource{
            return synchronized(ComentariosDataSource::class){
                val newInstance = INSTANCE?: ComentariosDataSource(resources)
                INSTANCE=newInstance
                newInstance
            }
        }
    }
}