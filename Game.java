import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;

//javafx bs
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Game extends javafx.application.Application {
  private static final int FRAME_WIDTH = 1366;
  private static final int FRAME_HEIGHT = 768;
  private JFrame frame;
  private JPanel panel;
  private JLabel label, mainMenu;
  private ImageIcon secretImage, mainScreenBG, playButtonImage, creditsButtonImage, exitButtonImage, playHoveredImage, creditsHoveredImage, exitHoveredImage, skipIntroButtonImage,
  skipIntroHoveredImage, intro1Image, companyLogo, titleScreen, intro2Image, intro3Image, intro4Image, intro5Image, intro6Image, intro7Image, intro8Image, intro9Image, intro10Image, 
  intro11Image, intro12Image, intro13Image, chapter1Image, tutorial1Image, tutorialProphecyImage;
  private JButton play, credits, exit, secretButt, skipIntro;
  private static MediaPlayer mediaPlayer;
  private SpringLayout layout;
  private JTextArea textArea;
  private int stringCounter = 0;
  private boolean introSkipped;
  
  //private JSlider slider;
  
  public Game() {    
    frame = new JFrame();
    panel = new JPanel();
    secretImage = new ImageIcon( getClass().getResource( "resources/Secret_Image.png" ) );
    mainScreenBG = new ImageIcon( getClass().getResource( "resources/Main_Screen_Image.gif" ) );
    playButtonImage = new ImageIcon( getClass().getResource( "resources/play button.png"  ) );
    creditsButtonImage = new ImageIcon( getClass().getResource( "/resources/credits button.png" ) );
    exitButtonImage = new ImageIcon( getClass().getResource( "resources/exit button.png" ) );
    playHoveredImage = new ImageIcon( getClass().getResource( "resources/Play Button Hovered.png" ) );
    creditsHoveredImage = new ImageIcon( getClass().getResource( "resources/Credits Button Hovered.png" ) );
    exitHoveredImage = new ImageIcon( getClass().getResource( "resources/Exit Button Hovered.png" ) );
    skipIntroButtonImage = new ImageIcon( getClass().getResource( "resources/Skip_Intro.png" ) );
    skipIntroHoveredImage = new ImageIcon( getClass().getResource( "resources/Skip_Intro_Hovered.png" ) );
    intro1Image = new ImageIcon( getClass().getResource( "resources/1_intro.gif" ) );
    intro2Image = new ImageIcon( getClass().getResource( "resources/2_intro.gif"  ) );
    intro3Image = new ImageIcon( getClass().getResource( "resources/3_intro.gif"  ) );
    intro4Image = new ImageIcon( getClass().getResource( "resources/4_intro.gif"  ) );
    intro5Image = new ImageIcon( getClass().getResource( "resources/5_intro.gif"  ) );
    intro6Image = new ImageIcon( getClass().getResource( "resources/6.1_intro.gif"  ) );
    intro7Image = new ImageIcon( getClass().getResource( "resources/7_intro.gif"  ) );
    intro8Image = new ImageIcon( getClass().getResource( "resources/8_intro.gif"  ) );
    intro9Image = new ImageIcon( getClass().getResource( "resources/9_intro.gif"  ) );
    intro10Image = new ImageIcon( getClass().getResource( "resources/10_intro.gif"  ) );
    intro11Image = new ImageIcon( getClass().getResource( "resources/11_intro.gif"  ) );
    intro12Image = new ImageIcon( getClass().getResource( "resources/12_intro.gif"  ) );
    intro13Image = new ImageIcon( getClass().getResource( "resources/13_intro.gif"  ) );
    chapter1Image = new ImageIcon( getClass().getResource( "resources/14_chapter.gif" ) );
    companyLogo = new ImageIcon( getClass().getResource( "resources/0_company.gif" ) );
    titleScreen = new ImageIcon( getClass().getResource( "resources/0.5_title.gif" ) );
    tutorial1Image = new ImageIcon( getClass().getResource( "resources/1_tutorial.gif" ) );
    tutorialProphecyImage = new ImageIcon( getClass().getResource( "resources/1.1_tutorial.gif" ) );
    play = new JButton( playButtonImage );
    credits = new JButton( creditsButtonImage );
    exit = new JButton( exitButtonImage );
    secretButt = new JButton();
    skipIntro = new JButton( skipIntroButtonImage );
    mainMenu = new JLabel( mainScreenBG );
    label = new FadeLabel( "Main_Screen_Image.gif", "0_company.gif" );
    textArea = new JTextArea( 2, 20 );
    setButtonTransparent( play );
    setButtonTransparent( credits );
    setButtonTransparent( exit );
    setButtonTransparent( secretButt );
    setButtonTransparent( skipIntro );
    skipIntro.setFocusPainted( false );
    mainMenu.setLayout( new GridBagLayout() );
    layout = new SpringLayout();
    panel.setLayout( layout );
    textArea.setPreferredSize( new Dimension( 1000, 100 ) );
    textArea.setBackground( new Color ( 5, 16, 88 ) );
    textArea.setForeground( new Color( 249, 253, 168 ) );
    textArea.setOpaque( true );
    textArea.setBorder( BorderFactory.createCompoundBorder( BorderFactory.createLineBorder( new Color( 249, 253, 168 ), 2, true ), 
        BorderFactory.createCompoundBorder( BorderFactory.createLineBorder( new Color( 57, 249, 191 ) , 5, true ), BorderFactory.createEmptyBorder( 10, 5, 5, 5 ) ) ) );
    textArea.setWrapStyleWord( true );
    textArea.setLineWrap( true );
    textArea.setEditable( false );
    textArea.setFocusable( false );
    
    
    GridBagConstraints c = new GridBagConstraints();
    c.gridx = 0;
    c.gridy = 1;
    c.weightx = 0.9;
    c.insets = new Insets( 0, 0, 30, 0 );
    mainMenu.add( play, c );
    c.gridy = 2;
    mainMenu.add( credits, c );
    c.gridy = 3;
    mainMenu.add( exit, c );
    c.gridy = 0;
    mainMenu.add( secretButt, c );
    
    try {
      mediaPlayer = new MediaPlayer( new Media( getClass().getResource( "resources/Blue_Bird_Song.mp3" ).toString() ) );
      setNewSong( "Blue_Bird_Song" );
      
      /*
      slider = new JSlider( 0, 100, 100 );
      c.gridy = 4;
      mainMenu.add( slider, c );
      slider.addChangeListener( new javax.swing.event.ChangeListener() {
        @Override
        public void stateChanged( javax.swing.event.ChangeEvent evt ) {
          if( evt.getSource().equals( slider ) ) {
            mediaPlayer.setVolume( 0.01 * slider.getValue() );
          }
        }
      });
      */
    } catch( Exception e ) {
      e.printStackTrace();
    }
    
    //adding new font
    try {
      GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
      ge.registerFont( Font.createFont( Font.TRUETYPE_FONT, getClass().getResourceAsStream( "resources/Pixel-Noir.ttf" ) ) );
    } catch( Exception e ) {
      e.printStackTrace();
    }
    
    label.setPreferredSize( new Dimension( FRAME_WIDTH, FRAME_HEIGHT ) );
    panel.add( textArea );
    textArea.setVisible( false );
    panel.add( mainMenu );
    panel.add( skipIntro );
    skipIntro.setVisible( false );
    panel.setPreferredSize( new Dimension( FRAME_WIDTH, FRAME_HEIGHT ) );
    play.addMouseListener( new MenuButtonMouseAdapter() );
    credits.addMouseListener( new MenuButtonMouseAdapter() );
    exit.addMouseListener( new MenuButtonMouseAdapter() );
    skipIntro.addMouseListener( new MenuButtonMouseAdapter() );
    play.addActionListener( new MenuButtonActionListener() );
    credits.addActionListener( new MenuButtonActionListener() );
    exit.addActionListener( new MenuButtonActionListener() );
    secretButt.addActionListener( new MenuButtonActionListener() );
    
    frame.add( panel );
    frame.setPreferredSize( new Dimension( FRAME_WIDTH, FRAME_HEIGHT ) );
    frame.pack();
    frame.setLocationRelativeTo( null );
    frame.setResizable( false );
    frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    frame.setTitle( "A N A" );
    frame.setVisible( true );
  }
  
  //checking if mouse enters the button for a hover effect
  private class MenuButtonMouseAdapter extends MouseAdapter {
    public void mouseEntered( MouseEvent evt ) {
      if( evt.getSource().equals( play ) ) {
        play.setIcon( playHoveredImage );
        } else if( evt.getSource().equals( credits ) ) {
        credits.setIcon( creditsHoveredImage );
        } else if( evt.getSource().equals( exit ) ) {
        exit.setIcon( exitHoveredImage );
        } else if( evt.getSource().equals( skipIntro ) ) {
        skipIntro.setIcon( skipIntroHoveredImage );
        }
      }
    
    //checking if mouse leaves button for unhovered effect
    public void mouseExited( MouseEvent evt ) {
      if( evt.getSource().equals( play ) ) {
        play.setIcon( playButtonImage );
        } else if( evt.getSource().equals( credits ) ) {
        credits.setIcon( creditsButtonImage );
        } else if( evt.getSource().equals( exit ) ) {
        exit.setIcon( exitButtonImage );
        } else if( evt.getSource().equals( skipIntro ) ) {
          skipIntro.setIcon( skipIntroButtonImage );
        }
      }
    }
  
  //mouse listener for detecting which menu button was pressed
  private class MenuButtonActionListener implements ActionListener {
    @Override
    public void actionPerformed( ActionEvent evt ) {
      if( evt.getSource().equals( play ) ) {
        play.setVisible( false );
        credits.setVisible( false );
        exit.setVisible( false );
        secretButt.setVisible( false );
        //slider.setVisible( false );
        setNewSong( "Psycho_Song" );
        panel.remove( mainMenu );
        panel.add( label );
        ( ( FadeLabel )label ).fadeImages();
        Timer timer = new Timer( 1900, new ActionListener() {
          @Override
          public void actionPerformed( ActionEvent arg0 ) {
            panel.remove( label );
            panel.add( new JLabel( companyLogo ) );
            panel.revalidate();
          }
        });
        timer.setRepeats(false);
        timer.start();
        timer = new Timer( 12000, new ActionListener() {
          @Override
          public void actionPerformed( ActionEvent arg0 ) {
            panel.remove( panel.getComponent( 2 ) );
            panel.add( new JLabel( titleScreen ) );
            panel.revalidate();
            label = new FadeLabel( "0.5_title.gif", "1_intro.gif", 1000 );
            label.setPreferredSize( new Dimension( FRAME_WIDTH, FRAME_HEIGHT ) );
          }
        });
        timer.setRepeats( false );
        timer.start();
        timer = new Timer( timer.getDelay() + 14000, new ActionListener() {
          @Override
          public void actionPerformed( ActionEvent arg0 ) {
            panel.remove( panel.getComponent( 2 ) );
            panel.add( label );
            panel.revalidate();
            ( ( FadeLabel )label ).fadeImages();
          }
        });
        timer.setRepeats( false );
        timer.start();
        timer = new Timer( timer.getDelay() + 1000, new ActionListener() {
          @Override
          public void actionPerformed( ActionEvent arg0 ) {
            for( int i = 2; i < panel.getComponentCount(); i++ ) {
              panel.remove( panel.getComponent( i ) );
            }
            panel.add( new JLabel( intro1Image ) );
            panel.revalidate();
          }
        });
        timer.setRepeats( false );
        timer.start();
        timer = new Timer( timer.getDelay() + 500, new ActionListener() {
          @Override
          public void actionPerformed( ActionEvent arg0 ) {
            startIntro();
          }
        });
        timer.setRepeats( false );
        timer.start();
      } else if( evt.getSource().equals( credits ) ) {
        mainMenu.setIcon( null );
        mainMenu.setText( "made by sum peeps" );
        mainMenu.revalidate();
      } else if( evt.getSource().equals(  exit ) ) {
        frame.dispatchEvent( new java.awt.event.WindowEvent( frame, java.awt.event.WindowEvent.WINDOW_CLOSING ) );
      } else if( evt.getSource().equals( secretButt ) ) {
        mainMenu.setIcon( secretImage );
        play.setVisible( false );
        credits.setVisible( false );
        //slider.setVisible( false );
        secretButt.setEnabled( false );
        mainMenu.revalidate();
      }
    }
  }
  
  //handles the starting of the intro
  private void startIntro() {
    layout.putConstraint( SpringLayout.NORTH, skipIntro, 5, SpringLayout.NORTH, panel );
    skipIntro.setVisible( true );
    skipIntro.addMouseListener( new MouseAdapter() {
      @Override
      public void mouseClicked( MouseEvent evt ) {
        panel.remove( panel.getComponent( 2 ) );
        panel.remove( skipIntro );
        panel.add( new JLabel( chapter1Image ) );
        textArea.setVisible( false );
        panel.revalidate();
        panel.addMouseListener( new MouseAdapter() {
          @Override
          public void mouseClicked( MouseEvent arg0 ) {
            startTutorial();
          }
        });
        introSkipped = true;
        mediaPlayer.stop();
      }
    });
    layout.putConstraint( SpringLayout.WEST, textArea, 183, SpringLayout.WEST, panel );
    layout.putConstraint( SpringLayout.NORTH, textArea, 618, SpringLayout.NORTH, panel );
    textArea.setVisible( true );
    animateText( "This is Allan. He works as a professional League of Legends player for the Shanghai Sharks. "
        + "Recently his life has been getting quite boring, and he's wondering how his childhood friends are doing.", 15 );
    textArea.setFont( new Font( "Pixel-Noir", Font.PLAIN, 15 ) );
    panel.revalidate();
    Timer t = new Timer( 6250, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        if( !introSkipped ) {
          textArea.setText( "" );
          animateText( "After dealing with the mental anxiety that comes with being a professional gamer, coupled with the low quality"
              + " internet spewing out of his busted router, Allan has decided to end it all...", 15 );
        }
      }
    });
    t.setRepeats( false );
    t.start();
    t = new Timer( t.getDelay() + 6250, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        if( !introSkipped ) {
          panel.remove( panel.getComponent( 2 ) );
          textArea.setText( "" );
          textArea.setVisible( false );
          panel.add( new JLabel( intro2Image ) );
          panel.revalidate();
          Timer tempTimer = new Timer( 500, new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e ) {
              textArea.setVisible( true );
              animateText( "...so he quit his profession and decided to venture out into the great beyond.", 25 );
            }
          });
          tempTimer.setRepeats( false );
          tempTimer.start();
        }
      }
    });
    t.setRepeats( false );
    t.start();
    t = new Timer( t.getDelay() + 4500, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        if( !introSkipped ) {
          panel.remove( panel.getComponent( 2 ) );
          panel.add( new JLabel( intro3Image ) );
          textArea.setVisible( false );
          textArea.setText( "" );
          panel.revalidate();
        }
      }
    });
    t.setRepeats( false );
    t.start();
    t = new Timer( t.getDelay() + 10500, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        if( !introSkipped ) {
          panel.remove( panel.getComponent( 2 ) );
          panel.add( new JLabel( intro4Image ) );
          panel.revalidate();
        }
      }
    });
    t.setRepeats( false );
    t.start();
    t = new Timer( t.getDelay() + 3500, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        if( !introSkipped ) {
          panel.remove( panel.getComponent( 2 ) );
          panel.add( new JLabel( intro5Image ) );
          panel.revalidate();
        }
      }
    });
    t.setRepeats( false );
    t.start();
    t = new Timer( t.getDelay() + 10000, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        if( !introSkipped ) {
          panel.remove( panel.getComponent( 2 ) );
          panel.add( new JLabel( intro6Image ) );
          textArea.setVisible( true );
          panel.revalidate();
          animateText( "Allan wonders why none of his friends have ever kept in touch with him as of recently. He texts the boys "
              + "back at Irvine with no avail. So that was it, his first destination to reunite with the squad.", 15 );
        }
      }
    });
    t.setRepeats( false );
    t.start();
    t = new Timer( t.getDelay() + 9000, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        if( !introSkipped ) {
          panel.remove( panel.getComponent( 2 ) );
          panel.add( new JLabel( intro7Image ) );
          textArea.setVisible( false );
          textArea.setText( "" );
          panel.revalidate();
        }
      }
    });
    t.setRepeats( false );
    t.start();
    t = new Timer( t.getDelay() + 5000, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        if( !introSkipped ) {
          panel.remove( panel.getComponent( 2 ) );
          panel.add( new JLabel( intro8Image ) );
          textArea.setVisible( true );
          panel.revalidate();
          animateText( "Deciding that his life was more enjoyable with the people that respected him, Allan leaves behind his drab "
              + "life to seek out the happiness that has been locked away for so long.", 25 );
          Timer tempTimer = new Timer( 7000, new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e ) {
              textArea.setText( "" );
              animateText( "The last glimpse of Shanghai fades into the background as the plane heads overseas.", 25 );
            }
          });
          tempTimer.setRepeats( false );
          tempTimer.start();
        }
      }
    });
    t.setRepeats( false );
    t.start();
    t = new Timer( t.getDelay() + 13500, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        if( !introSkipped ) {
          panel.remove( panel.getComponent( 2 ) );
          panel.add( new JLabel( intro9Image ) );
          textArea.setVisible( false );
          textArea.setText( "" );
          panel.revalidate();
        }
      }
    });
    t.setRepeats( false );
    t.start();
    t = new Timer( t.getDelay() + 7000, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        if( !introSkipped ) {
          panel.remove( panel.getComponent( 2 ) );
          panel.add( new JLabel( intro10Image ) );
          panel.revalidate();
        }
      }
    });
    t.setRepeats( false );
    t.start();
    t = new Timer( t.getDelay() + 7000, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        if( !introSkipped ) {
          panel.remove( panel.getComponent( 2 ) );
          panel.add( new JLabel( intro11Image ) );
          textArea.setVisible( true );
          panel.revalidate();
          animateText( "The train ride back to the motherland was very nostalgic. Allan breathes in the fresh air he once knew as a child and relishes it.", 25 );
        }
      }
    });
    t.setRepeats( false );
    t.start();
    t = new Timer( t.getDelay() + 13500, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        if( !introSkipped ) {
          panel.remove( panel.getComponent( 2 ) );
          panel.add( new JLabel( intro12Image ) );
          textArea.setVisible( false );
          textArea.setText( "" );
          panel.revalidate();
        }
      }
    });
    t.setRepeats( false );
    t.start();
    t = new Timer( t.getDelay() + 14000, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        if( !introSkipped ) {
          panel.remove( panel.getComponent( 2 ) );
          panel.add( new JLabel( intro13Image ) );
          textArea.setVisible( true );
          panel.revalidate();
          animateText( "Irvine's glistening buildings and green trees come into full view as the train comes to a stop.\r\n" + 
              "\r\nWelcome back friend. Welcome back to your roots.\r\n", 35 );
          Timer tempTimer = new Timer( 11000, new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e ) {
              textArea.setVisible( false );
              textArea.setText( "" );
            }
          });
          tempTimer.setRepeats( false );
          tempTimer.start();
        }
      }
    });
    t.setRepeats( false );
    t.start();
    t = new Timer( t.getDelay() + 13000, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        if( !introSkipped ) {
          panel.remove( panel.getComponent( 2 ) );
          panel.add( new JLabel( chapter1Image ) );
          panel.revalidate();
          panel.addMouseListener( new MouseAdapter() {
            @Override
            public void mouseClicked( MouseEvent arg0 ) {
              startTutorial();
            }
          });
          Timer tempTimer = new Timer( 800, new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e ) {
              mediaPlayer.stop();
            }
          });
          tempTimer.setRepeats( false );
          tempTimer.start();
        }
      }
    });
    t.setRepeats( false );
    t.start();
  }
  
  private void startTutorial() {
    MouseListener [] arr = panel.getMouseListeners();
    panel.removeMouseListener( arr[ 0 ] );
    panel.remove( panel.getComponent( 1 ) );
    label = new FadeLabel( "14_chapter.gif", "1_tutorial.gif", 3000 );
    label.setPreferredSize( new Dimension( 1366, 768 ) );
    panel.add( label );
    panel.revalidate();
    ( ( FadeLabel )label ).fadeImages();
    Timer t = new Timer( 2900, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        panel.remove( label );
        panel.add( new JLabel( tutorial1Image ) );
        panel.revalidate();
      }
    });
    t.setRepeats( false );
    t.start();
  }
  
  private void animateText( String text, int time ) {
    final Timer t = new Timer( time, null );
    t.addActionListener( new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        textArea.setText( text.substring( 0, stringCounter ) );
        stringCounter++;
        
        if( stringCounter > text.length() ) {
          t.stop();
          stringCounter = 0;
          return;
        }
      }
    });
    t.start();
  }
  
  private void setButtonTransparent( JButton b ) {
    b.setOpaque( false );
    b.setContentAreaFilled( false );
    b.setBorderPainted( false );
  }
  
  private void setNewSong( String songName ) {
    mediaPlayer.stop();
    mediaPlayer = new MediaPlayer( new Media( getClass().getResource( "resources/" + songName + ".mp3" ).toString() ) );
    mediaPlayer.play();
    mediaPlayer.setOnEndOfMedia( new Runnable() {
      @Override
      public void run() {
        mediaPlayer.seek( Duration.ZERO );
        mediaPlayer.play();
      }
    });
  }
  
  @SuppressWarnings("serial")
  private static class FadeLabel extends JLabel {

    private long runningTime = 2000;

    private BufferedImage inImage;
    private BufferedImage outImage;

    private float alpha = 0f;
    private long startTime = -1;

    private Timer timer;
    
    private boolean firstTime = true;
    
    public FadeLabel( String image1, String image2 ) {
        try {
            outImage = ImageIO.read( getClass().getResource( "resources/" + image1 ) );
            inImage = ImageIO.read( getClass().getResource( "resources/" + image2) );
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        timer = new Timer( 40, new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e ) {
                if ( startTime < 0 ) {
                    startTime = System.currentTimeMillis();
                } else {

                    long time = System.currentTimeMillis();
                    long duration = time - startTime;
                    if( duration >= runningTime ) {
                        startTime = -1;
                        ( ( Timer )e.getSource() ).stop();
                        alpha = 0f;
                    } else {
                        alpha = 1f - ( ( float )duration / ( float )runningTime );
                    }
                    repaint();
                }
            }
        });
    }
    
    public FadeLabel( String image1, String image2, long runningTime ) {
      this.runningTime = runningTime;
      try {
          outImage = ImageIO.read( getClass().getResource( "resources/" + image1 ) );
          inImage = ImageIO.read( getClass().getResource( "resources/" + image2) );
      } catch ( Exception e ) {
          e.printStackTrace();
      }

      timer = new Timer( 40, new ActionListener() {
          @Override
          public void actionPerformed( ActionEvent e ) {
              if ( startTime < 0 ) {
                  startTime = System.currentTimeMillis();
              } else {

                  long time = System.currentTimeMillis();
                  long duration = time - startTime;
                  if( duration >= runningTime ) {
                      startTime = -1;
                      ( ( Timer )e.getSource() ).stop();
                      alpha = 0f;
                  } else {
                      alpha = 1f - ( ( float )duration / ( float )runningTime );
                  }
                  repaint();
              }
          }
      });
  }
    
    public void fadeImages() {
      alpha = 0f;
      BufferedImage tmp = inImage;
      inImage = outImage;
      outImage = tmp;
      timer.start();
    }

    @Override
    protected void paintComponent( Graphics g ) {
        super.paintComponent( g );
        Graphics2D g2d = ( Graphics2D )g.create();
        g2d.setComposite( AlphaComposite.SrcOver.derive( alpha ) );
        int x = ( getWidth() - inImage.getWidth() ) / 2;
        int y = ( getHeight() - inImage.getHeight() ) / 2;
        if( !firstTime ) {
          g2d.drawImage( inImage, x, y, this );
        }

        g2d.setComposite( AlphaComposite.SrcOver.derive( 1f - alpha ) );
        x = ( getWidth() - outImage.getWidth() ) / 2;
        y = ( getHeight() - outImage.getHeight() ) / 2;
        if( !firstTime ) {
          g2d.drawImage( outImage, x, y, this );
        }
        firstTime = false;
        g2d.dispose();
    }
  }
  
  @Override
  public void start( Stage arg0 ) throws Exception { }
  
  public static void main( String [] args ) {
    new Game();
  }
}