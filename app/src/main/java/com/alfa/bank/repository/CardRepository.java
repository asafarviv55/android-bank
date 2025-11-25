package com.alfa.bank.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.alfa.bank.model.Card;

import java.util.ArrayList;
import java.util.List;

public class CardRepository {
    private static CardRepository instance;
    private MutableLiveData<List<Card>> cardsLiveData;

    private CardRepository() {
        cardsLiveData = new MutableLiveData<>();
        loadMockData();
    }

    public static CardRepository getInstance() {
        if (instance == null) {
            instance = new CardRepository();
        }
        return instance;
    }

    public LiveData<List<Card>> getCards() {
        return cardsLiveData;
    }

    private void loadMockData() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card("CARD001", "**** **** **** 5678", "Visa Platinum", "12/2026", "***", "ACC001", true, 10000.00, 8500.00));
        cards.add(new Card("CARD002", "**** **** **** 9999", "Mastercard Gold", "09/2025", "***", "ACC002", true, 5000.00, 4200.00));
        cards.add(new Card("CARD003", "**** **** **** 3456", "Amex Blue", "03/2027", "***", "ACC001", false, 15000.00, 15000.00));
        cardsLiveData.setValue(cards);
    }

    public void toggleCardStatus(String cardId) {
        List<Card> cards = cardsLiveData.getValue();
        if (cards != null) {
            for (Card card : cards) {
                if (card.getCardId().equals(cardId)) {
                    card.setActive(!card.isActive());
                    cardsLiveData.setValue(cards);
                    break;
                }
            }
        }
    }
}
