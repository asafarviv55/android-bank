package com.alfa.bank.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.alfa.bank.model.Card;
import com.alfa.bank.repository.CardRepository;

import java.util.List;

public class CardViewModel extends ViewModel {
    private CardRepository repository;
    private LiveData<List<Card>> cards;

    public CardViewModel() {
        repository = CardRepository.getInstance();
        cards = repository.getCards();
    }

    public LiveData<List<Card>> getCards() {
        return cards;
    }

    public void toggleCardStatus(String cardId) {
        repository.toggleCardStatus(cardId);
    }
}
