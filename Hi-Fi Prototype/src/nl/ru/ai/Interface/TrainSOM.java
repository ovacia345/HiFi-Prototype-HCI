package nl.ru.ai.Interface;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TrainSOM {
	private static final String[] SIZES = { "SMALL", "MEDIUM", "LARGE" };

	private static final int STARTING_TEST_INDEX = 0;
	private static final int NUMBER_OF_TESTS = 3;
	private static final int NUMBER_OF_IMAGES_PER_TEST = 60;

	//Added function by Niels van Nistelrooij
	public static DataVector readVectorPublic( String fileName ) {
		return readVector( fileName );
	}
	//Added function by Niels van Nistelrooij
		
	private static DataVector readVector( String fileName ) {
		BufferedImage image = null;

		try {
			image = ImageIO.read( new File( fileName ) );
		} catch ( IOException e ) {

		}

		if ( image == null ) {
			return null;
		}

		DataVector vector = new DataVector();

		for ( int y = 0; y < image.getHeight(); ++y ) {
			for ( int x = 0; x < image.getWidth(); ++x ) {
				vector.vector.add( ( ( ( image.getRGB( x, y ) ) & 0x00FFFFFF ) == 0x00000000 ) ? 1.0d : 0.0d );
			}
		}

		return vector;
	}

	public static void main( String[] args ) {
		SelfOrganisingMap map = new SelfOrganisingMap( "map_data\\all_vectors_1500_750.txt" );

		Polygon_t[] correctClassifications = Polygon_t.values();
		int numberOfImagesPerClassification = NUMBER_OF_IMAGES_PER_TEST / correctClassifications.length;
		int numberOfImagesPerSize = numberOfImagesPerClassification / SIZES.length;
		int correct = 0;

		for ( int i = STARTING_TEST_INDEX; i < NUMBER_OF_TESTS; ++i ) {
			int totalNumberOfImages = i * NUMBER_OF_IMAGES_PER_TEST;

			for ( int j = 0; j < NUMBER_OF_IMAGES_PER_TEST; ++j ) {
				Polygon_t currentClassification = map.getClassification( readVector( "test_data\\" + Integer.toString( totalNumberOfImages + j ) + ".bmp" ) );
				System.out.print( currentClassification.toString() + ", " + correctClassifications[j / numberOfImagesPerClassification] );

				if ( currentClassification == correctClassifications[j / numberOfImagesPerClassification] ) {
					correct++;
					System.out.print( '\n' );
				} else {
					System.out.println( " (" + SIZES[( j % numberOfImagesPerClassification ) / numberOfImagesPerSize] + ") Not correct! Image " + ( totalNumberOfImages + j ) );
				}
			}
		}

		System.out.println( (double)correct / (double)( ( NUMBER_OF_TESTS - STARTING_TEST_INDEX ) * NUMBER_OF_IMAGES_PER_TEST ) * 100.0d );
	}
}
