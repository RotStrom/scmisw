(ns core
  (:require [clojure.core.reducers :as r]))

(defn recursive-sum [numbers]
  (if (empty? numbers)
    0
    (+ (first numbers) (recursive-sum (rest numbers)))
    )
  )

(defn reduce-sum [numbers]
  (reduce (fn [acc x] (+ acc x)) 0 numbers)
  )

(defn reduce-sum-2 [numbers] (reduce + numbers))

(defn parallel-sum [numbers]
  (r/fold + numbers))

(def numbers (into [] (range 0 1000)))

(println "recursive-sum:")
(println (time (recursive-sum numbers)))
(println "reduce-sum:")
(println (time (reduce-sum numbers)))
(println "reduce-sum-2:")
(println (time (reduce-sum-2 numbers)))
(println "parallel-sum:")
(println (time (parallel-sum numbers)))

(def counts {"apple" 2 "orange" 1})
(get counts "apple" 0)

(println (map inc [1 2 3 4 5]))
(println (map (fn [x] (* 2 x)) [1 2 3 4 5]))

(def multiply-by-2 (partial * 2))
(multiply-by-2 3)
(map (partial * 2) [1 2 3 4 5])

(defn get-words [text] (re-seq #"\w+" text))
(println (get-words "one two three four"))
(println (map get-words ["a b c" "f d e" "f g h"]))
(println (mapcat get-words ["a b c" "b c d" "c d e"]))
