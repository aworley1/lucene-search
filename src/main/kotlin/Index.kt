import org.apache.lucene.analysis.standard.StandardAnalyzer
import org.apache.lucene.document.Document
import org.apache.lucene.document.Field
import org.apache.lucene.document.TextField
import org.apache.lucene.index.IndexWriter
import org.apache.lucene.index.IndexWriterConfig
import org.apache.lucene.store.ByteBuffersDirectory

object Index {
    val index = ByteBuffersDirectory()
    val analyzer = StandardAnalyzer()
    val indexWriterConfig = IndexWriterConfig(analyzer)
    val writer = IndexWriter(index, indexWriterConfig)

    init {
        val data = mapOf(
            "blah blah blah" to "1",
            "three blind mice" to "2",
            "little bo peep" to "3",
            "hello world" to "4",
            "i am the walrus" to "5",
        )

        data.forEach {
            add(it.key, it.value)
        }
    }

    private fun add(name: String, id: String) {
        val document = Document().apply {
            add(TextField("name", name, Field.Store.YES))
            add(TextField("id", id, Field.Store.YES))
        }

        writer.addDocument(document)

        writer.commit()
    }
}