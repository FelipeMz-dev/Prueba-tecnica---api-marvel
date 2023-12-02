package com.mz_dev.prueba_tecnica

import com.mz_dev.prueba_tecnica.data.MarvelRepository
import com.mz_dev.prueba_tecnica.data.local.datasource.LocalCharacterDataSource
import com.mz_dev.prueba_tecnica.data.local.datasource.LocalSeriesDataSource
import com.mz_dev.prueba_tecnica.data.local.model.LocalCharacter
import com.mz_dev.prueba_tecnica.data.local.model.LocalSerie
import com.mz_dev.prueba_tecnica.data.mapper.LocalToCharacterMapper
import com.mz_dev.prueba_tecnica.data.mapper.LocalToSerieMapper
import com.mz_dev.prueba_tecnica.data.remote.datasource.RemoteCharacterDataSource
import com.mz_dev.prueba_tecnica.data.remote.datasource.RemoteSerieDataSource
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.times
import org.mockito.kotlin.verifyBlocking

class MarvelRepositoryTest {
    @Test
    fun `When DB is empty, server is called`() {
        val localCharacterDataSource = mock<LocalCharacterDataSource> {
            onBlocking { countCharacters() } doReturn 0
        }
        val localSeriesDataSource = mock<LocalSeriesDataSource> {
            onBlocking { countSeries() } doReturn 0
        }
        val remoteCharacterDataSource = mock<RemoteCharacterDataSource>{
            onBlocking { getCharacters(any(), any()) } doReturn emptyList()
        }
        val remoteSerieDataSource = mock<RemoteSerieDataSource>{
            onBlocking { getSeries(any(), any()) } doReturn emptyList()
        }
        val repository = MarvelRepository(
            localCharacterDataSource,
            remoteCharacterDataSource,
            remoteSerieDataSource,
            localSeriesDataSource
        )
        //test for loadCharacters
        runBlocking { repository.loadCharacters(10) }
        verifyBlocking(remoteCharacterDataSource) { getCharacters(any(), any()) }
        //test for loadSeries
        runBlocking { repository.loadSeries(10) }
        verifyBlocking(remoteSerieDataSource) { getSeries(any(), any()) }
    }

    @Test
    fun `When DB is not empty, server is not called`() {
        val localCharacterDataSource = mock<LocalCharacterDataSource> {
            onBlocking { countCharacters() } doReturn 1
        }
        val localSeriesDataSource = mock<LocalSeriesDataSource> {
            onBlocking { countSeries() } doReturn 1
        }
        val remoteCharacterDataSource = mock<RemoteCharacterDataSource>{
            onBlocking { getCharacters(any(), any()) } doReturn emptyList()
        }
        val remoteSerieDataSource = mock<RemoteSerieDataSource>{
            onBlocking { getSeries(any(), any()) } doReturn emptyList()
        }
        val repository = MarvelRepository(
            localCharacterDataSource,
            remoteCharacterDataSource,
            remoteSerieDataSource,
            localSeriesDataSource
        )
        //test for loadCharacters
        runBlocking { repository.loadCharacters(10) }
        verifyBlocking(remoteCharacterDataSource, never()) { getCharacters(any(), any()) }
        //test for loadSeries
        runBlocking { repository.loadSeries(10) }
        verifyBlocking(remoteSerieDataSource, never()) { getSeries(any(), any()) }
    }

    @Test
    fun `When DB is empty, characters and series are saved into DB`(){
        val expectedCharacter = LocalCharacter(0, "name", "description", "")
        val expectedSerie = LocalSerie(0, "name", "description", "")
        val localCharacterDataSource = mock<LocalCharacterDataSource> {
            onBlocking { countCharacters() } doReturn 0
        }
        val localSeriesDataSource = mock<LocalSeriesDataSource> {
            onBlocking { countSeries() } doReturn 0
        }
        val remoteCharacterDataSource = mock<RemoteCharacterDataSource>{
            onBlocking {
                getCharacters(any(), any())
            } doReturn listOf(LocalToCharacterMapper().map(expectedCharacter))
        }
        val remoteSerieDataSource = mock<RemoteSerieDataSource>{
            onBlocking {
                getSeries(any(), any())
            } doReturn listOf(LocalToSerieMapper().map(expectedSerie))
        }
        val repository = MarvelRepository(
            localCharacterDataSource,
            remoteCharacterDataSource,
            remoteSerieDataSource,
            localSeriesDataSource
        )
        //test for loadCharacters
        runBlocking { repository.loadCharacters(20) }
        verifyBlocking(localCharacterDataSource) {
            insertAll(listOf(LocalToCharacterMapper().map(expectedCharacter)))
        }
        //test for loadSeries
        runBlocking { repository.loadSeries(10) }
        verifyBlocking(localSeriesDataSource) {
            insertAll(listOf(LocalToSerieMapper().map(expectedSerie)))
        }
    }

    @Test
    fun `When DB is not empty, characters and series are not saved into DB`() {
        val localCharacterDataSource = mock<LocalCharacterDataSource> {
            onBlocking { countCharacters() } doReturn 1
        }
        val localSeriesDataSource = mock<LocalSeriesDataSource> {
            onBlocking { countSeries() } doReturn 1
        }
        val remoteCharacterDataSource = mock<RemoteCharacterDataSource> {
            onBlocking { getCharacters(any(), any()) } doReturn emptyList()
        }
        val remoteSerieDataSource = mock<RemoteSerieDataSource> {
            onBlocking { getSeries(any(), any()) } doReturn emptyList()
        }
        val repository = MarvelRepository(
            localCharacterDataSource,
            remoteCharacterDataSource,
            remoteSerieDataSource,
            localSeriesDataSource
        )
        //test for loadCharacters
        runBlocking { repository.loadCharacters(10) }
        verifyBlocking(localCharacterDataSource, never()) { insertAll(any()) }
        //test for loadSeries
        runBlocking { repository.loadSeries(10) }
        verifyBlocking(localSeriesDataSource, never()) { insertAll(any()) }
    }

    @Test
    fun `Verify load a number of times when load remote elements`() {
        val localCharacterDataSource = mock<LocalCharacterDataSource> {
            onBlocking { countCharacters() } doReturn 0
        }
        val localSeriesDataSource = mock<LocalSeriesDataSource> {
            onBlocking { countSeries() } doReturn 0
        }
        val remoteCharacterDataSource = mock<RemoteCharacterDataSource> {
            onBlocking { getCharacters(any(), any()) } doReturn emptyList()
        }
        val remoteSerieDataSource = mock<RemoteSerieDataSource> {
            onBlocking { getSeries(any(), any()) } doReturn emptyList()
        }
        val repository = MarvelRepository(
            localCharacterDataSource,
            remoteCharacterDataSource,
            remoteSerieDataSource,
            localSeriesDataSource
        )
        //test for loadCharacters
        runBlocking { repository.loadCharacters(110) }
        verifyBlocking(remoteCharacterDataSource, times(6)) {
            getCharacters(any(), any())
        }
        //test for loadSeries
        runBlocking { repository.loadSeries(110) }
        verifyBlocking(remoteSerieDataSource, times(6)) {
            getSeries(any(), any())
        }
    }

    @Test
    fun `Verify load a size elements when load remote elements with a quantity`() {
        val localCharacterDataSource = mock<LocalCharacterDataSource> {
            onBlocking { countCharacters() } doReturn 0
        }
        val localSeriesDataSource = mock<LocalSeriesDataSource> {
            onBlocking { countSeries() } doReturn 0
        }
        val remoteCharacterDataSource = mock<RemoteCharacterDataSource> {
            onBlocking { getCharacters(any(), any()) } doReturn emptyList()
        }
        val remoteSerieDataSource = mock<RemoteSerieDataSource> {
            onBlocking { getSeries(any(), any()) } doReturn emptyList()
        }
        val repository = MarvelRepository(
            localCharacterDataSource,
            remoteCharacterDataSource,
            remoteSerieDataSource,
            localSeriesDataSource
        )
        //test for loadCharacters
        runBlocking { repository.loadCharacters(60) }
        verifyBlocking(remoteCharacterDataSource) {
            getCharacters(20, 40)
        }
        //test for loadSeries
        runBlocking { repository.loadSeries(60) }
        verifyBlocking(remoteSerieDataSource) {
            getSeries(20, 40)
        }
    }
}