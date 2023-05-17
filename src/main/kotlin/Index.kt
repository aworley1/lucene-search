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
        val data = listOf(
            "Apple" to "iPhone 11",
            "Apple" to "iPhone 12",
            "Samsung" to "Galaxy 3",
            "Samsung" to "Galaxy 4",
            "Nokia" to "3210",
        )

        data.forEach {
            add(it.first, it.second)
        }

        writer.close()
    }

    private fun add(make: String, model: String) {
        val document = Document().apply {
            add(TextField("make", make, Field.Store.YES))
            add(TextField("model", model, Field.Store.YES))
        }

        writer.addDocument(document)

        writer.commit()
    }
}