package com.sabrina.rickAndMorty.domain

import com.sabrina.rickAndMorty.data.dto.ResponseCharacters
import kotlinx.coroutines.flow.Flow
import com.sabrina.rickAndMorty.vo.Resource
import com.sabrina.rickAndMorty.data.dto.Character



interface RepoCharacters {

    fun getCharacters(pageNum: Int): Flow<Resource<ResponseCharacters>>

    fun getCharacter(id: Int): Flow<Resource<Character>>

}
