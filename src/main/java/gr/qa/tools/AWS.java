package gr.qa.tools;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.RawMessage;
import com.amazonaws.services.simpleemail.model.SendRawEmailRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * This is a sample class with useful AWS methods.
 * They are not working, unless we have a valid AWS account
 */
public class AWS {

    private final static Logger logger = LogManager.getLogger(AWS.class);

    protected BasicAWSCredentials awsCredentials;
    protected AmazonS3 s3;

    /**
     * Sets the credentials for S3 connection
     * @param accessKey : the access key of the user
     * @param secretKey : the secret key of the user
     */
    public void setAwsCredentials(String accessKey, String secretKey) {
        awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
    }
    
    /* S3 */

    /**
     * Connect to S3 in a region
     * @param region : the region we want to connect to
     */
    public void connectToS3(String region) {
        s3 = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(region)
                .build();
        logger.info("==============================");
        logger.info("Getting started with Amazon S3");
        logger.info("==============================");
    }

    /**
     * Lists the buckets in your account
     */
    public void listBuckets() {
        try {
            logger.info("Listing buckets...");
            for (Bucket bucket : s3.listBuckets()) {
                logger.info(" - " + bucket.getName());
            }
        }
        catch (AmazonServiceException ase) {
            logger.error("Caught an AmazonServiceException, which means that the requested made it to Amazon S3, " +
                    "but was rejected with an error response");
            logger.error("Error message:    " + ase.getMessage());
            logger.error("HTTP status code: " + ase.getStatusCode());
            logger.error("AWS error code:   " + ase.getErrorCode());
            logger.error("Error Type:       " + ase.getErrorType());
            logger.error("Request ID:       " + ase.getRequestId());
        }
        catch (AmazonClientException ace) {
            logger.error("Caught an AmazonClientException, which means that the client encountered " +
                    "a serious internal problem while trying to communicate with S3, such as not being " +
                    "able to access the network.");
            logger.error("Error message:    " + ace.getMessage());
        }
    }

    /**
     * Lists objects that exist in a bucket
     * @param bucketName : bucket's name
     */
    public void listObjectsInBucket(String bucketName) {
        try {
            logger.info("Listing objects in bucket '" + bucketName + "'");
            ObjectListing objectListing = s3.listObjects(new ListObjectsRequest().withBucketName(bucketName));
            List<S3ObjectSummary> allObjectSummariesList = new ArrayList<>(objectListing.getObjectSummaries());
            while (objectListing.isTruncated()) {
                objectListing = s3.listNextBatchOfObjects((objectListing));
                allObjectSummariesList.addAll(objectListing.getObjectSummaries());
            }
            for (S3ObjectSummary objectSummary : allObjectSummariesList) {
                logger.info(" - " + objectSummary.getKey() + " (size = " + objectSummary.getSize() + ")");
            }
        }
        catch (AmazonServiceException ase) {
            logger.error("Caught an AmazonServiceException, which means that the requested made it to Amazon S3, " +
                    "but was rejected with an error response");
            logger.error("Error message:    " + ase.getMessage());
            logger.error("HTTP status code: " + ase.getStatusCode());
            logger.error("AWS error code:   " + ase.getErrorCode());
            logger.error("Error Type:       " + ase.getErrorType());
            logger.error("Request ID:       " + ase.getRequestId());
        }
        catch (AmazonClientException ace) {
            logger.error("Caught an AmazonClientException, which means that the client encountered " +
                    "a serious internal problem while trying to communicate with S3, such as not being " +
                    "able to access the network.");
            logger.error("Error message:    " + ace.getMessage());
        }
    }

    /* Other Actions */

    public void sendEmail(String sender, String recipient, String subject, String body, String region) {
        try {
            Session session = Session.getDefaultInstance(new Properties());
            // Create a new MimeMessage object
            MimeMessage mimeMessage = new MimeMessage(session);
            // Add "subject", "from" and "to" lines
            mimeMessage.setSubject(subject, "UTF-8");
            mimeMessage.setFrom(new InternetAddress(sender));
            mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            // Create a multipart/alternative child container
            MimeMultipart msg_body = new MimeMultipart("alternative");
            // Create a wrapper for the HTML and text parts
            MimeBodyPart wrap = new MimeBodyPart();
            // Define the text part
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setContent(body, "text/plain; charset=UTF-8");
            // Add the text and HTML parts to the child container
            msg_body.addBodyPart(textPart);
            // Add the child container to the wrapper object
            wrap.setContent(msg_body);
            // Create a multipart/mixed parent container
            MimeMultipart msg = new MimeMultipart("mixed");
            // Add the parent container to the message
            mimeMessage.setContent(msg);
            // Add the multipart/alternative part to the message
            msg.addBodyPart(wrap);
            // Try to send the email
            logger.info("Sending email from " + sender + " to " + recipient + " with subject: " + subject);
            // Instantiate an Amazon SES client which will make the service call with the supplied AWS credentials
            AmazonSimpleEmailService client = AmazonSimpleEmailServiceClientBuilder
                                                .standard()
                                                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                                                // Replace US_WEST_2 with the AWS Region you're using for Amazon SES
                                                .withRegion(region)
                                                .build();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            mimeMessage.writeTo(outputStream);
            RawMessage rawMessage = new RawMessage(ByteBuffer.wrap(outputStream.toByteArray()));
            SendRawEmailRequest sendRawEmailRequest = new SendRawEmailRequest(rawMessage);
            client.sendRawEmail(sendRawEmailRequest);
            logger.info("Email sent.");
        }
        catch (Exception e) {
            logger.error("Sending email has failed!");
            logger.error("Error message:    " + e.getMessage());
            e.printStackTrace();
        }
    }

}
