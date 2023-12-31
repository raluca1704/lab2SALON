package org.salon_frumusete.controller;

//import org.salon_frumusete.databasemodell.AnniversaryGiftDecorator;
import org.salon_frumusete.databasemodell.LoyaltyCard;
//import org.salon_frumusete.databasemodell.LoyaltyCardDecorator;
import org.salon_frumusete.repository.LoyaltyCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loyaltycards")
public class LoyaltyCardController {

    @Autowired
    private LoyaltyCardRepository loyaltyCardRepository;


    @PostMapping
    public ResponseEntity<LoyaltyCard> addLoyaltyCard(@RequestBody LoyaltyCard loyaltyCard) {
        // Salvati loyaltyCard in repository
        LoyaltyCard savedLoyaltyCard = loyaltyCardRepository.save(loyaltyCard);

//        // Decorati loyaltyCard cu AnniversaryGiftDecorator
//        LoyaltyCardDecorator anniversaryGiftCard = new AnniversaryGiftDecorator(savedLoyaltyCard);
//
//        // Salvati loyaltyCard decorat in repository
//        LoyaltyCard savedAnniversaryGiftCard = loyaltyCardRepository.save(anniversaryGiftCard);
//
//        // Returnati loyaltyCard decorat
        return ResponseEntity.ok(savedLoyaltyCard);
    }

    @GetMapping("/{loyaltyCardId}")
    public ResponseEntity<LoyaltyCard> getLoyaltyCardById(@PathVariable int loyaltyCardId) {
        return loyaltyCardRepository.findById(loyaltyCardId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<LoyaltyCard>> getAllLoyaltyCards() {
        return ResponseEntity.ok(loyaltyCardRepository.findAll());
    }

    @PutMapping("/{loyaltyCardId}")
    public ResponseEntity<LoyaltyCard> updateLoyaltyCard(@PathVariable int loyaltyCardId, @RequestBody LoyaltyCard loyaltyCardDetails) {
        return loyaltyCardRepository.findById(loyaltyCardId)
                .map(loyaltyCard -> {
                    loyaltyCard.setDiscount(loyaltyCardDetails.getDiscount());
                    return ResponseEntity.ok(loyaltyCardRepository.save(loyaltyCard));
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{loyaltyCardId}")
    public ResponseEntity<Void> deleteLoyaltyCard(@PathVariable int loyaltyCardId) {
        return loyaltyCardRepository.findById(loyaltyCardId)
                .map(loyaltyCard -> {
                    loyaltyCardRepository.delete(loyaltyCard);
                    return ResponseEntity.ok().<Void>build();
                }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<LoyaltyCard> getLoyaltyCardByClientId(@PathVariable int clientId) {
        LoyaltyCard loyaltyCard = loyaltyCardRepository.findByClientID(clientId);
        if (loyaltyCard != null) {
            return ResponseEntity.ok(loyaltyCard);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}