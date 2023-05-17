import org.apache.lucene.index.DirectoryReader
import org.apache.lucene.index.Term
import org.apache.lucene.search.IndexSearcher
import org.apache.lucene.search.MatchAllDocsQuery
import org.apache.lucene.search.PrefixQuery

fun main() {
    val reader = DirectoryReader.open(Index.index)
    val searcher = IndexSearcher(reader)

    val query = MatchAllDocsQuery()
    val results = searcher.search(query, Int.MAX_VALUE)

    println(results.totalHits)

    results.scoreDocs.map {
        searcher.indexReader.storedFields().document(it.doc).getField("make").stringValue()
    }.distinct().also { println(it) }

}