package messageutil

import org.yaml.snakeyaml.DumperOptions
import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.AbstractConstruct
import org.yaml.snakeyaml.constructor.Constructor
import org.yaml.snakeyaml.nodes.Node
import org.yaml.snakeyaml.nodes.ScalarNode
import org.yaml.snakeyaml.nodes.Tag
import org.yaml.snakeyaml.representer.Represent
import org.yaml.snakeyaml.representer.Representer

/** [Type]のyaml上のタグ. */
val typeTag = "!type"

private class TypeConstructor : Constructor() {
    init {
        yamlConstructors.put(Tag(typeTag), ConstructType())
    }

    private inner class ConstructType : AbstractConstruct() {
        override fun construct(node: Node): Any {
            val str = constructScalar(node as ScalarNode) as String
            return Type.parse(str)
        }
    }
}


private class TypeRepresenter : Representer() {
    init {
        representers.put(Type::class.java, RepresentType())
    }

    private inner class RepresentType : Represent {
        override fun representData(data: Any): Node {
            val type = data as Type
            return representScalar(Tag(typeTag), type.toSimpleString())
        }
    }
}


private val dumperOptions = DumperOptions().apply {
    defaultFlowStyle = DumperOptions.FlowStyle.BLOCK
    isAllowReadOnlyProperties = true
    isAllowUnicode = true
}

/** 適当に設定された[Yaml]インスタンス. */
val myYaml = Yaml(TypeConstructor(), TypeRepresenter(), dumperOptions)