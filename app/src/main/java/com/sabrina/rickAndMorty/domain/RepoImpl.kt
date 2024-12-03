package com.sabrina.rickAndMorty.domain

import com.sabrina.rickAndMorty.data.dto.ResponseCharacters
import kotlinx.coroutines.flow.Flow
import com.sabrina.rickAndMorty.vo.Resource
import javax.inject.Inject


class RepoImpl @Inject constructor(var repoCharacters: RepoCharacters) {
    operator fun invoke(page: Int): Flow<Resource<ResponseCharacters>> {
        return repoCharacters.getCharacters(page)
    }
}