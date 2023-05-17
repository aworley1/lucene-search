import org.apache.lucene.index.DirectoryReader
import org.apache.lucene.index.Term
import org.apache.lucene.search.IndexSearcher
import org.apache.lucene.search.TermQuery

fun main() {
    val reader = DirectoryReader.open(Index.index)
    val searcher = IndexSearcher(reader)

    val query = TermQuery(Term("name", "peep"))
    val results = searcher.search(query, 5)

    val doc = results.scoreDocs.first()
    println(searcher.indexReader.storedFields().document(doc.doc))
}