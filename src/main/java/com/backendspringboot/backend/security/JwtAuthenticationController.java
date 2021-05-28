package com.backendspringboot.backend.security;

import com.backendspringboot.backend.dto.UserDto;
import com.backendspringboot.backend.entity.Users;
import com.backendspringboot.backend.security.*;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RoleNotFoundException;


@RestController
@CrossOrigin
public class JwtAuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;


    @RequestMapping(value="/login",method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        final Users user1 = userDetailsService.getUserByUsername(authenticationRequest.getUsername());
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        //final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        //final String token = jwtTokenUtil.generateToken(userDetails);
        final Users user = userDetailsService.getUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken2(user);
        final String refreshToken = jwtTokenUtil.generateRefreshToken(user);
        return ResponseEntity.ok(new JwtResponse(token,refreshToken));
    }
    @PostMapping(value="/login/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshRequest refreshRequest){
        String refreshToken = refreshRequest.refreshToken.substring(7);
        JwtResponse tokens = jwtTokenUtil.refreshJwtToken(refreshToken);
        return ResponseEntity.ok().body(tokens);

    }
    @GetMapping(value="/getUser")
    public Object getUser(@RequestHeader("Authorization") String Token){
        String token  = Token.substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        Users user = userDetailsService.getUserByUsername(username);
        return ResponseEntity.ok(user);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody UserDto user) throws Exception {
        return ResponseEntity.ok(userDetailsService.save(user));
    }
    @PostMapping(value="/updateUser")
    public Object updateUser(@RequestBody UserDto user){
        Users updateUser = userDetailsService.getUserByUsername(user.getUsername());
//        Set<PiecePrice> piecePrices = updateUser.getPiecePrices();
//        for(PiecePrice price :piecePrices){
//            price.setLat(user.getLat());
//            price.setLng(user.getLng());
//            price.setAddresse(user.getAddresse());
//            piecePriceService.savePiecePrice(price);
//        }
        return ResponseEntity.ok(userDetailsService.updateUser(user));
    }

//    @PostMapping("/piecePrice/{idPiece}")
//    public Object addPiecePrice(@RequestHeader("Authorization") String Token, @Valid @RequestBody PiecePriceDto piecePriceDto, @PathVariable("idPiece") long idPiece){
//        String token = Token.substring(7);
//        PiecePrice piecePrice = modelMapper.map(piecePriceDto,PiecePrice.class);
//        String username = jwtTokenUtil.getUsernameFromToken(token);
//        Users user = userDetailsService.getUserByUsername(username);
//        Piece piece = pieceService.getPieceById(idPiece);
//        System.out.println(piece.getIdPiece());
//        piecePrice.setPiece(piece);
//        piecePrice.setUser(user);
//        piecePrice.setAddresse(user.getAddresse());
//        piecePrice.setLat(user.getLat());
//        piecePrice.setLng(user.getLng());
//        System.err.println(piecePrice.getAddresse());
//        piecePriceService.savePiecePrice(piecePrice);
//        return ResponseEntity.status(HttpStatus.CREATED).body(user);
//    }
//    @GetMapping("/fourniePiece")
//    public Object getUserPiece(@RequestHeader("Authorization") String Token){
//        String token = Token.substring(7);
//        String username = jwtTokenUtil.getUsernameFromToken(token);
//        Users user = userDetailsService.getUserByUsername(username);
//        Set<PiecePrice> piecePrices = user.getPiecePrices();
//        Type listType = new TypeToken<Set<PiecePriceDto>>(){}.getType();
//        Set<PiecePriceDto> piecePriceDtos= modelMapper.map(piecePrices,listType);
//        return ResponseEntity.status(HttpStatus.OK).body(piecePriceDtos);
//    }
//    @GetMapping("/allFourniePiece")
//    public Object getAllFourniePiece() throws Exception {
//        List<Users> usersList = userDetailsService.getAllFournie();
//        Set<PiecePrice> piecePrices = new HashSet<>();
//        for(Users user:usersList){
//            piecePrices.addAll(user.getPiecePrices());
//        }
//        Type listType = new TypeToken<Set<PiecePriceDto>>(){}.getType();
//        Set<PiecePriceDto> piecePriceDtos= modelMapper.map(piecePrices,listType);
//
//        return ResponseEntity.status(HttpStatus.OK).body(piecePriceDtos);
//    }

    @RequestMapping(value = "/getRole",method = RequestMethod.GET)
    public Object getRole(@RequestHeader("Authorization") String Token ) throws RoleNotFoundException {
        String token = Token.substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JSONObject roleObject = new JSONObject();
        roleObject.put("role",userDetailsService.getRole(username));
        return ResponseEntity.status(HttpStatus.OK).body(roleObject);
    }

    private void authenticate(String username,String password) throws Exception{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        }catch (DisabledException e){
            throw new Exception("USER_DISABLED",e);
        }catch (BadCredentialsException E){
            throw new Exception("INVALID_CREDENTIALS",E);
        }

    }
//    @GetMapping("/piecesAvailable")
//    public Object getPieceAvailable(@RequestHeader("Authorization") String Token){
//        String token = Token.substring(7);
//        String username = jwtTokenUtil.getUsernameFromToken(token);
//        Users user = userDetailsService.getUserByUsername(username);
//        return ResponseEntity.ok(piecePriceService.piecePriceQuery(user.getId()));
//    }
}