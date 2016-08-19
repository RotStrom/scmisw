(def numbers (range 0 1000000000))

(println (take 10 numbers))
(println (take 10 (map (partial * 2) numbers)))

(println (take 10 (iterate inc 0)))
(println (take 10 (iterate (partial + 2) 0)))

(println (take-last 5 (range 0 100000000)))