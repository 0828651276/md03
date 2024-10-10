<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Shop in Style</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            padding-top: 56px;
        }
        .hero-section {
            background-image: url('/thiet-ke-noi-that-shop-quan-ao-nam.jpg'); /* Replace with the actual path to your uploaded image */
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
            color: black;
            text-align: center;
            padding-top: 90px;
            height: 60vh;
        }
        .product-card {
            margin-bottom: 30px;
        }
        .product-card img {
            width: 100%;
            height: 400px;
            object-fit: cover;
        }
    </style>
</head>
<body>

<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
    <div class="container">
        <a class="navbar-brand" href="#">Shop Queen</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="/index.jsp">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/about.jsp">About</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Cart</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/admin/login">Login</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<!-- Hero Section -->
<section class="hero-section">
    <div class="container">
        <h1>Shop For Your Style</h1>
    </div>
</section>

<!-- Product Grid -->
<div class="container my-4">
    <div class="row">
        <!-- Product 1 -->
        <div class="col-lg-3 col-md-6 mb-4">
            <div class="card h-100 product-card">
                <img class="card-img-top" src="https://drake.vn/image/catalog/H%C3%ACnh%20content/hinh-anh-giay-vans/hinh-anh-giay-vans-17.jpg" alt="Product Image">
                <div class="card-body text-center">
                    <h5 class="card-title">Vans Shoe</h5>
                    <p class="card-text">$50.00 - $80.00</p>
                    <div class="text-warning">★★★★★</div>
                </div>
                <div class="card-footer text-center">
                    <a href="#" class="btn btn-outline-dark">Add to cart</a>
                </div>
            </div>
        </div>
        <!-- Product 2 -->
        <div class="col-lg-3 col-md-6 mb-4">
            <div class="card h-100 product-card">
                <img class="card-img-top" src="https://th.bing.com/th/id/OIP.auv8r1n7qhNs5JCj48dSbgHaJH?rs=1&pid=ImgDetMain" alt="Product Image">
                <div class="card-body text-center">
                    <h5 class="card-title">Nike Shoe</h5>
                    <p class="text-muted"><del>$60.00</del> <strong>$52.00</strong></p>
                    <div class="text-warning">★★★★★</div>
                </div>
                <div class="card-footer text-center">
                    <a href="#" class="btn btn-outline-dark">Add to cart</a>
                </div>
            </div>
        </div>
        <!-- Product 3 -->
        <div class="col-lg-3 col-md-6 mb-4">
            <div class="card h-100 product-card">
                <img class="card-img-top" src="https://th.bing.com/th/id/OIP.dv-7dFoNB053wAak2MHn2gHaLG?w=184&h=276&c=7&r=0&o=5&pid=1.7" alt="Product Image">
                <div class="card-body text-center">
                    <h5 class="card-title">Ao Dai - Net Dep Viet Nam</h5>
                    <p class="text-muted"><del>$500.00</del> <strong>$350.00</strong></p>
                    <div class="text-warning">★★★★★</div>
                </div>
                <div class="card-footer text-center">
                    <a href="#" class="btn btn-outline-dark">Add to cart</a>
                </div>
            </div>
        </div>
        <!-- Product 4 -->
        <div class="col-lg-3 col-md-6 mb-4">
            <div class="card h-100 product-card">
                <img class="card-img-top" src="https://th.bing.com/th/id/OIP.9FYFS18uCwjuGUlnw23byQHaHa?rs=1&pid=ImgDetMain" alt="Product Image">
                <div class="card-body text-center">
                    <h5 class="card-title">Chunky White Swarovski Pearl Necklace</h5>
                    <p><strong>$40.00</strong></p>
                    <div class="text-warning">★★★★★</div>
                </div>
                <div class="card-footer text-center">
                    <a href="#" class="btn btn-outline-dark">Add to cart</a>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
