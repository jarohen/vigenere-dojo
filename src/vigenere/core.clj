(ns vigenere.core)

(def a-to-z (map char (range (int \a )  (inc (int \z)))) )

(def grid (take 26 (partition 26 1 (cycle a-to-z))) )

(def grid-map 
  (zipmap a-to-z
          (map (fn [letter-list]
                 (zipmap a-to-z letter-list))
               grid)))

(comment
  (take 100 (cycle a-to-z)))

(defn encode-letter [letter key-letter]
  (get-in grid-map [key-letter letter]))

(defn encode-text [plaintext passphrase]
  (when (seq passphrase)
    (apply str (map encode-letter plaintext (cycle passphrase)))))

(comment
  (map + [1 2 3] [3 10 5 6 9]))
