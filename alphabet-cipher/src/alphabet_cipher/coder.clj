(ns alphabet-cipher.coder)

(def letters (into [] (map char (range (int \a) (inc (int \z))))))

(defn rotate [v n] 
  (if (<= n 0)
      v
      (rotate (conj (into [] (rest v)) (first v)) (dec n))))

(defn lookup [row col]
  (nth (rotate letters (- (int col) (int \a))) (- (int row) (int \a))))

(defn rev-lookup [row col] (char (+ (int \a) (mod (- (int row) (int col)) 26))))

(defn encode [keyword message]
  (let [len (count message)
        e  (int (Math/ceil (/ len (count keyword))))
        extended-key (take len (apply str (repeat e keyword)))]
    (apply str (map lookup message extended-key))))

(defn decode [keyword message]
  (let [len (count message)
        e  (int (Math/ceil (/ len (count keyword))))
        extended-key (take len (apply str (repeat e keyword)))]
    (apply str (map rev-lookup message extended-key))))

