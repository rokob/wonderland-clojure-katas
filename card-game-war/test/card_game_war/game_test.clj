(ns card-game-war.game-test
  (:require [clojure.test :refer :all]
            [card-game-war.game :refer :all]))


;; fill in  tests for your game
(deftest test-play-round
  (testing "the highest rank wins the cards in the round"
    (let [player1-card [:spade 7]
          player2-card [:spade 3]]
      (is (= :player1 (play-round player1-card player2-card)))))
  (testing "queens are higer rank than jacks"
    (let [player1-card [:spade :queen]
          player2-card [:spade :jack]]
      (is (= :player1 (play-round player1-card player2-card)))))
  (testing "kings are higer rank than queens"
    (let [player1-card [:spade :queen]
          player2-card [:spade :king]]
      (is (= :player2 (play-round player1-card player2-card)))))
  (testing "aces are higer rank than kings"
    (let [player1-card [:spade :ace]
          player2-card [:club :king]]
      (is (= :player1 (play-round player1-card player2-card)))))
  (testing "if the ranks are equal, clubs beat spades"
    (let [player1-card [:spade :queen]
          player2-card [:club :queen]]
      (is (= :player2 (play-round player1-card player2-card)))))
  (testing "if the ranks are equal, diamonds beat clubs"
    (let [player1-card [:club :queen]
          player2-card [:diamond :queen]]
      (is (= :player2 (play-round player1-card player2-card)))))
  (testing "if the ranks are equal, hearts beat diamonds"
    (let [player1-card [:heart :jack]
          player2-card [:diamond :jack]]
      (is (= :player1 (play-round player1-card player2-card))))))

(deftest test-play-game
  (testing "the player loses when they run out of cards"
    (let [player1-cards [[:spade :ace], [:heart 2]]
          player2-cards [[:club 6], [:club 2]]]
      (is (= :player1 (play-game player1-cards player2-cards))))))

