(ns collatz)

(defn not-zero? [x] (not= x 0))

(defn not-one? [x] (not= x 1))

(defn iter [x] (if (not-one? x) (if (even? x) (/ x 2) (+ (* x 3) 1)) 0))

; Memoize for speed (trade-off is memory)
(def iter-memo (memoize iter))

(defn collatz [x] (take-while not-zero? (iterate iter-memo x)))

; Memoize for speed (trade-off is memory)
(def collatz-memo (memoize collatz))

(defn get-num-iters [x] (count (collatz-memo x)))

(def get-num-iters-memo (memoize get-num-iters))

(defn num-iters-over-range [start-val end-val] (map get-num-iters-memo (range start-val end-val)))

(defn get-hwm [start-val end-val] (reduce max (num-iters-over-range start-val end-val)))

(defn get-lwm [start-val end-val] (reduce min (num-iters-over-range start-val end-val)))

