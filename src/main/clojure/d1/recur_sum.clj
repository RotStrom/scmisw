(defn summm [numbers]
  (loop [xs numbers acc 0]
    (if (empty? xs) acc
      (recur (rest xs) (+ acc (first xs)))
      )
    )
  )

(print "sum: ")
(println (summm (range 1 20)))
(println (summm (range 1 20 0.5)))
(println (summm (map read-string ["1" "2" "3"])))
(println (summm [1.0 1 2/3]))


(defn reduce-sum [numbers]
  (reduce (fn [acc x] (+ acc x)) 0 numbers)
  )

(defn reduce-sum-2 [numbers] (reduce #(+ %1 %2) 0 numbers))
(println (reduce-sum-2 [1 2 3 4 5 6 7 8 9]))
;(println (summm ["1" "2" "3"]))