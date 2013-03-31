; -*-Lisp-*-

;;;;
;;;;Parse an OPML file for atom/rss feeds
;;;;
(ns opml
    (:require clojure.xml))

(defn parse-opml[url]
  "Takes a URL thet MUST locate a valid OPML resource."
      (clojure.xml/parse url))

(defn body [opml-seq]
  "Return the body elements of a parsed OPML sequence"
  (first (filter 
   #( = :body (% :tag)) 
   (opml-seq :content))))

(defn body-content [body]
  "Returns the content of a body"
  (body :content))

(defn make-feed [feed-info]
  "If feed-info is info on a single feed, extract and return a map of that data
   If feed-info is a recursive structure of feeds, return a flattened list
   of feed data maps."
  (if (nil? (feed-info :content))
      [{:text ((feed-info :attrs) :text),
      :title ((feed-info :attrs) :title),
      :xmlUrl ((feed-info :attrs) :xmlUrl)
      :htmlUrl ((feed-info :attrs) :htmlUrl) }]
      (mapcat make-feed (feed-info :content))))	

(defn print-feed [feed]
  "Print out a single feed's info tidily"
  (println "Feed:" (feed :text))
  (println "\tTitle: " (feed :title))
  (println "\tXML URL: " (feed :xmlUrl))
  (println "\tHTML URL: " (feed :htmlUrl)))

(defn subscriptions [opml-url]
    "Return a list of subscriptions from an OPML file"
    (mapcat make-feed
	 (body-content 
	  (body 
	   (parse-opml opml-url)))))

(defn opml-test []
  (let [opml "http://localhost/reader/subscriptions.xml"]
       (doseq [feed (subscriptions opml)]
	      (print-feed feed))))

(opml-test)
