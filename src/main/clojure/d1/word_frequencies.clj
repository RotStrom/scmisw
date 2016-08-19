(defn word-frequencies [words]
  (reduce
    (fn [counts word] (assoc counts word (inc (get counts word 0))))
    {} words
    )
  )

(def words ["one" "one" "one" "two" "at" "the" "end"])

(println (word-frequencies words))
(println (frequencies words))
