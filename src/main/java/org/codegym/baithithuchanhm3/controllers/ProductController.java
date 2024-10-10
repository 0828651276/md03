package org.codegym.baithithuchanhm3.controllers;

import org.codegym.baithithuchanhm3.services.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ProductController", urlPatterns = {"/admin/products/*"})
public class ProductController extends BaseController {
    private ProductService productService;


    @Override
    public void init() throws ServletException {
        productService = new ProductService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String url = req.getPathInfo();
        if (url == null) {
            url = "/";
        }
        try {
            switch (url) {
                case "/":
                    productService.showPageProductList(req, resp);
                    break;
                case "/create":
                    productService.showPageAddProduct(req, resp);
                    break;
                case "/update":
                    productService.showPageUpdateProduct(req, resp);
                    break;
                case "/delete":
                    productService.deleteProduct(req, resp);
                    break;

                default:
                    renderPage404(req, resp);
            }
        }catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getPathInfo();
        if (url == null) {
            url = "/";
        }

        try {
            switch (url) {
                case "/create":
                    productService.createProduct(req, resp);
                    break;
                case "/update":
                    productService.updateProduct(req, resp);
                    break;
                default:
                    renderPage404(req, resp);
            }
        }catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }
}


