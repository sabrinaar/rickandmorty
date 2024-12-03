package com.sabrina.rickAndMorty.data.api

import com.sabrina.rickAndMorty.data.repositories.RepoCharactersImpl
import com.sabrina.rickAndMorty.domain.RepoCharacters
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoriesModule {

    @Binds
    abstract fun bindCharacterRepository(impl: RepoCharactersImpl): RepoCharacters
}