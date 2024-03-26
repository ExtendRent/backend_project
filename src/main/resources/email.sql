-- mail_templates tablosuna veri ekleme

IF NOT EXISTS (select 1 from mail_templates where id = 1) THEN
  insert into mail_templates (id, created_at, updated_at, template_name, content, mail_subject, mail_from, mail_from_name)
  values
  (1, NOW(), NOW(), 'EMAIL_CONFIRMATION','
            <!DOCTYPE html>
            <html lang="en">
            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>ExtendRent Kayıt Doğrulama</title>
                <style>
                    body {
                        font-family: Arial, sans-serif;
                        background-color: #f4f4f4;
                        text-align: center;
                    }

                    .container {
                        max-width: 600px;
                        margin: 20px auto;
                        padding: 20px;
                        background-color: #ffffff;
                        border-radius: 8px;
                        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                    }

                    h2 {
                        color: #333;
                    }

                    p {
                        color: #555;
                    }

                    .verification-link {
                        display: inline-block;
                        padding: 10px 20px;
                        background-color: #007bff;
                        color: #ffffff;
                        text-decoration: none;
                        border-radius: 5px;
                    }
                </style>
            </head>
            <body>
                <div class="container">
                    <h2>Kaydınızı Doğrulayın</h2>
                    <p>Merhaba {username},</p>
                    <p>Kaydınızı tamamlamak için aşağıdaki bağlantıya tıklayın:</p>
                    <a href="{verification_link}" class="verification-link">Hesabınızı Doğrula</a>
                    <p>Ya da bu bağlantıyı tarayıcınızın adres çubuğuna yapıştırın: {verification_link}</p>
                    <p>Eğer bu işlemi siz yapmadıysanız, bu e-postayı görmezden gelin.</p>
                    <p>Teşekkür ederiz!</p>
                </div>
            </body>
            </html>
            '
            , 'Epostanızı Doğrulayın', 'gokhanasilturkk@gmail.com', 'ExtendRent Company');
  END IF;
