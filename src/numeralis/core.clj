(ns numeralis.core)

(def ^:private base-mappings
  {0  "zero"
   1  "one"
   2  "two"
   3  "three"
   4  "four"
   5  "five"
   6  "six"
   7  "seven"
   8  "eight"
   9  "nine"
   10 "ten"
   11 "eleven"
   12 "twelve"
   13 "thirteen"
   14 "fourteen"
   15 "fifteen"
   16 "sixteen"
   17 "seventeen"
   18 "eighteen"
   19 "nineteen"
   20 "twenty"
   30 "thirty"
   40 "forty"
   50 "fifty"
   60 "sixty"
   70 "seventy"
   80 "eighty"
   90 "ninety"})

(defn- magnitudes [n]
  "Given a number n smaller than one trillion, return its most significant magnitude."
  (cond
    (and (>= n 10) (< n 100))                           {:magnitude 10 :symbol nil}
    (and (>= n 100) (< n 1000))                         {:magnitude 100 :symbol "hundred"}
    (and (>= n 1000) (< n (Math/pow 10 6)))             {:magnitude 1000 :symbol "thousand"}
    (and (>= n (Math/pow 10 6)) (< n (Math/pow 10 9)))  {:magnitude 1000000 :symbol "million"}
    (and (>= n (Math/pow 10 9)) (< n (Math/pow 10 12))) {:magnitude 1000000000 :symbol "billion"}))

(defn- first-hundred->english [n]
  "Given a positive whole number n within 0 to 99, return its representation in British English."
  (let [most-significant-magnitude (->> (Math/max (long n) 1) Math/log10 long (Math/pow 10) long)
        most-significant-digit     (long (/ n most-significant-magnitude))
        most-significant-value     (* most-significant-digit most-significant-magnitude)
        remaining-value            (- n most-significant-value)]
    (case most-significant-magnitude
      1  (get base-mappings most-significant-value)
      10 (if-let [predefined (get base-mappings n)]
           predefined
           (str (get base-mappings most-significant-value)
                (when (> remaining-value 0)
                  (str "-" (get base-mappings remaining-value)))))
      nil)))

(defn number->english [n]
  {:pre [(and (>= n 0) (< n (Math/pow 10 12)))]}
  "Given a number within 0 (inclusive) and one trillion (not inclusive),
   return its representation in British English."
  (loop [current  n
         sentence ""]
    (if-let [small-number (first-hundred->english current)]
      (str sentence small-number)
      (let [most-significant-magnitude (->> current Math/log10 long (Math/pow 10) long)
            magnitude-representation   (magnitudes most-significant-magnitude)
            most-significant-value     (long (/ current (:magnitude magnitude-representation)))
            represented-value          (* most-significant-value (:magnitude magnitude-representation))
            remaining-value            (- current represented-value)
            connecting-string          (fn [n] (cond
                                                 (and (< n 100) (> n 0)) " and "
                                                 (> n 0)                 ", "))
            updated-sentence           (str sentence
                                            (number->english most-significant-value)
                                            " "
                                            (:symbol magnitude-representation)
                                            (connecting-string remaining-value))]
        (if (> remaining-value 0)
          (recur remaining-value updated-sentence)
          updated-sentence)))))
