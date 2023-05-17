import org.apache.lucene.index.DirectoryReader
import org.apache.lucene.index.Term
import org.apache.lucene.search.IndexSearcher
import org.apache.lucene.search.PrefixQuery

fun main() {
    val reader = DirectoryReader.open(Index.index)
    val searcher = IndexSearcher(reader)

    val query = PrefixQuery(Term("model", "iphone"))
    val results = searcher.search(query, 5)

    println(results.totalHits)

    results.scoreDocs.forEach {
        println(searcher.indexReader.storedFields().document(it.doc))
    }

}