package com.sabrina.rickAndMorty.data.repositories

import com.sabrina.rickAndMorty.data.api.CharacteresApi
import com.sabrina.rickAndMorty.data.dto.Character
import com.sabrina.rickAndMorty.data.dto.ResponseCharacters
import com.sabrina.rickAndMorty.domain.RepoCharacters
import com.sabrina.rickAndMorty.vo.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class RepoCharactersImpl @Inject constructor(
    var api: CharacteresApi
) : RepoCharacters {


    override fun getCharacters(pageNum: Int): Flow<Resource<ResponseCharacters>> =
        flow {
            try {
                val response = api.getCharacters(pageNum)
                emit(Resource.Success(response))
            } catch (e: HttpException) {
                emit(
                    Resource.Error(
                        message = "Ups, algo salió mal",
                        data = null
                    )
                )
            } catch (e: IOException) {
                emit(
                    Resource.Error(
                        message = "Error de red",
                        data = null
                    )
                )
            }
        }

    override fun getCharacter(id: Int): Flow<Resource<Character>> =
        flow {
            try {
                val response = api.getCharacter(id)
                emit(Resource.Success(response))
            } catch (e: HttpException) {
                emit(
                    Resource.Error(
                        message = "Ups, algo salió mal",
                        data = null
                    )
                )
            } catch (e: IOException) {
                emit(
                    Resource.Error(
                        message = "Error de red",
                        data = null
                    )
                )
            }

        }
}


