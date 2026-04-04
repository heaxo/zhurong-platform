package com.zhurong.platform.base.masterlink.commands;

import com.zhurong.platform.base.masterlink.core.BaseXmlCommand;
import com.zhurong.platform.base.masterlink.core.XmlConditions;
import com.zhurong.platform.base.masterlink.core.XmlField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ProductOperationsCommand extends BaseXmlCommand {

    private static final String DEFAULT_OPERATION = "2D Cut";

    private final ProductsCommand product;
    private final String commandName;
    private final String tableName = "PRODUCT OPERATIONS";

    public ProductOperationsCommand(ProductsCommand product) {
        this(product, "IMPORT");
    }

    public ProductOperationsCommand(ProductsCommand product, String commandName) {
        this.product = product;
        this.commandName = commandName;
    }

    @Override
    public String getCommandName() {
        return commandName;
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public List<XmlField> getFields() {
        return filterFields(
                new XmlField("Product", product.getPrdRef()),
                new XmlField("WorkCenter", product.getWrkRef()),
                new XmlField(
                        "Operation",
                        product.getOperation() != null ? product.getOperation() : DEFAULT_OPERATION
                )
        );
    }

    @Override
    public List<XmlConditions> getConditions() {
        return null;
    }
}