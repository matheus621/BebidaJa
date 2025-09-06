package com.example.bebidaja.ui.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bebidaja.data.ProductRepository
import com.example.bebidaja.domain.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: ProductRepository,
    private val savedState: SavedStateHandle,
) : ViewModel() {

    private val KEY_TAB = "home_tab"
    private val KEY_QUERY = "home_query"
    private val KEY_SEARCHING = "home_searching"

    private val _selectedTab = MutableStateFlow(savedState[KEY_TAB] ?: 0)
    val selectedTab: StateFlow<Int> = _selectedTab

    private val _query = MutableStateFlow(savedState[KEY_QUERY] ?: "")
    val query: StateFlow<String> = _query

    private val _isSearching = MutableStateFlow(savedState[KEY_SEARCHING] ?: false)
    val isSearching: StateFlow<Boolean> = _isSearching

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products

    private val typeKeys = listOf("cerveja","destilado","vinho","semalcool")

    val filtered: StateFlow<List<Product>> =
        combine(_products, _selectedTab, _query) { list, tab, q ->
            val type = typeKeys.getOrNull(tab) ?: typeKeys.first()
            list.filter { p ->
                p.type.equals(type, true) &&
                        (q.isBlank() || p.name.contains(q, true) || p.description.contains(q, true))
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    init {
        viewModelScope.launch { _products.value = repo.listAll() }
    }

    fun setTab(index: Int) { _selectedTab.value = index; savedState[KEY_TAB] = index }
    fun setQuery(q: String) { _query.value = q; savedState[KEY_QUERY] = q }
    fun toggleSearch(open: Boolean? = null) {
        val v = open ?: !_isSearching.value
        _isSearching.value = v; savedState[KEY_SEARCHING] = v
        if (!v) setQuery("")
    }
}
