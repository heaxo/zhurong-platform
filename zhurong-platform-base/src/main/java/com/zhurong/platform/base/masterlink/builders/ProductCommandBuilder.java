package com.zhurong.platform.base.masterlink.builders;

import com.zhurong.platform.base.masterlink.commands.ProductImportgeoCommand;
import com.zhurong.platform.base.masterlink.commands.ProductOperationsCommand;
import com.zhurong.platform.base.masterlink.commands.ProductsCommand;
import com.zhurong.platform.base.masterlink.core.IXmlCommand;

import java.util.ArrayList;
import java.util.List;

public class ProductCommandBuilder {

    private final List<IXmlCommand> commands = new ArrayList<>();

    public ProductCommandBuilder withProduct(ProductsCommand product) {
        commands.add(product);
        return this;
    }

    public ProductCommandBuilder withOperations(ProductsCommand product) {
        commands.add(new ProductOperationsCommand(product));
        return this;
    }

    public ProductCommandBuilder withGeometry(ProductsCommand product) {
        commands.add(new ProductImportgeoCommand(product));
        return this;
    }

    public ProductCommandBuilder with(List<ProductsCommand> list) {
        for (ProductsCommand cmd : list) {
            commands.add(cmd);
            commands.add(new ProductOperationsCommand(cmd));
            commands.add(new ProductImportgeoCommand(cmd));
        }
        return this;
    }

    public List<IXmlCommand> build() {
        return commands;
    }
}