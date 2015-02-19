(ns card-game-war.game)

(def suits [:spade :club :diamond :heart])
(def ranks [2 3 4 5 6 7 8 9 10 :jack :queen :king :ace])
(def cards
  (for [suit suits
        rank ranks]
    [suit rank]))

(defn play-round [player1-card player2-card]
  (let [suit-diff (- (.indexOf suits (first player1-card))
                     (.indexOf suits (first player2-card)))
        rank-diff (- (.indexOf ranks (second player1-card))
                     (.indexOf ranks (second player2-card)))]
    (cond
      (> rank-diff 0) :player1
      (< rank-diff 0) :player2
      (> suit-diff 0) :player1
      (< suit-diff 0) :player2
      :else :tie)))

(defn play-game [player1-cards player2-cards]
  (cond
    (= 0 (count player1-cards)) :player2
    (= 0 (count player2-cards)) :player1
    :else (let [round-winner (play-round (first player1-cards) (first player2-cards))]
            (cond
              (= :player1 round-winner)
                (play-game (conj (into [] (rest player1-cards)) (first player2-cards) (first player1-cards))
                           (rest player2-cards))
              (= :player2 round-winner)
                (play-game (rest player1-cards)
                           (conj (into [] (rest player2-cards)) (first player1-cards) (first player2-cards)))
              :else :tie))))

